package org.kangning.lifejourney.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.kangning.lifejourney.exception.BusinessException;
import org.kangning.lifejourney.exception.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * JWT工具类
 * 用于生成、解析和验证JWT Token
 * 使用com.auth0:java-jwt库
 */
@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String JWT_PREFIX = "jwt:";
    private static final String JWT_USER_PREFIX = "jwt:user:";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    @Value("${jwt.refresh-expiration}")
    private int jwtRefreshExpirationMs;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 从Token中获取用户名
     */
    public String getUsernameFromJwtToken(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
    }

    /**
     * 从Token中获取过期时间
     */
    public Date getExpirationDateFromJwtToken(String token) {
        return extractClaim(token, DecodedJWT::getExpiresAt);
    }

    /**
     * 从Token中提取指定的声明
     */
    public <T> T extractClaim(String token, Function<DecodedJWT, T> claimsResolver) {
        final DecodedJWT jwt = decodeJwt(token);
        return claimsResolver.apply(jwt);
    }

    /**
     * 解码JWT Token
     */
    private DecodedJWT decodeJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new BusinessException(ErrorEnum.AUTH_TOKEN_INVALID);
        }
    }


    /**
     * 验证Token是否有效
     */
    public Boolean validateJwtToken(String authToken) {
        // 优先从Redis验证Token
        if (StringUtils.hasText(authToken)) {
            Boolean existsInRedis = redisTemplate.hasKey(JWT_PREFIX + authToken);
            if (existsInRedis != null && !existsInRedis) {
                logger.error("JWT token not exists in Redis");
                return false;
            }
        }
        
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(authToken);
            return true;
        } catch (SignatureVerificationException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (TokenExpiredException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            // 从Redis中删除过期的Token
            deleteTokenFromRedis(authToken);
        } catch (JWTVerificationException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            // 从Redis中删除无效的Token
            deleteTokenFromRedis(authToken);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    
    /**
     * 将Token存入Redis
     */
    public void storeTokenInRedis(String token, String username, String tokenType, int expirationMs) {
        try {
            // 存储Token信息到Redis，以Token为键
            redisTemplate.opsForValue().set(JWT_PREFIX + token, username, expirationMs, TimeUnit.MILLISECONDS);
            
            // 存储用户的Token列表，以用户名为键
            String userTokenKey = JWT_USER_PREFIX + username + ":" + tokenType;
            redisTemplate.opsForValue().set(userTokenKey, token, expirationMs, TimeUnit.MILLISECONDS);
            
            logger.debug("Token stored in Redis for user: {}", username);
        } catch (Exception e) {
            logger.error("Failed to store token in Redis: {}", e.getMessage());
        }
    }
    
    
    /**
     * 从Redis删除Token
     */
    public void deleteTokenFromRedis(String token) {
        try {
            // 先获取Token对应的用户名
            Object usernameObj = redisTemplate.opsForValue().get(JWT_PREFIX + token);
            if (usernameObj != null) {
                String username = usernameObj.toString();
                
                // 删除Token键
                redisTemplate.delete(JWT_PREFIX + token);
                
                // 删除用户的AccessToken和RefreshToken键
                redisTemplate.delete(JWT_USER_PREFIX + username + ":access");
                redisTemplate.delete(JWT_USER_PREFIX + username + ":refresh");
                
                logger.debug("Token deleted from Redis for user: {}", username);
            }
        } catch (Exception e) {
            logger.error("Failed to delete token from Redis: {}", e.getMessage());
        }
    }
    
    /**
     * 从Redis删除用户的所有Token
     */
    public void deleteUserTokensFromRedis(String username) {
        try {
            // 获取用户的AccessToken
            String accessTokenKey = JWT_USER_PREFIX + username + ":access";
            Object accessTokenObj = redisTemplate.opsForValue().get(accessTokenKey);
            if (accessTokenObj != null) {
                redisTemplate.delete(JWT_PREFIX + accessTokenObj.toString());
            }
            
            // 获取用户的RefreshToken
            String refreshTokenKey = JWT_USER_PREFIX + username + ":refresh";
            Object refreshTokenObj = redisTemplate.opsForValue().get(refreshTokenKey);
            if (refreshTokenObj != null) {
                redisTemplate.delete(JWT_PREFIX + refreshTokenObj.toString());
            }
            
            // 删除用户的Token键
            redisTemplate.delete(accessTokenKey);
            redisTemplate.delete(refreshTokenKey);
            
            logger.debug("All tokens deleted from Redis for user: {}", username);
        } catch (Exception e) {
            logger.error("Failed to delete user tokens from Redis: {}", e.getMessage());
        }
    }

    /**
     * 生成访问Token
     */
    public String generateAccessToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "access");
        String token = createToken(claims, username, jwtExpirationMs);
        
        // 将生成的Token存入Redis
        storeTokenInRedis(token, username, "access", jwtExpirationMs);
        
        return token;
    }

    /**
     * 生成刷新Token
     */
    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");
        String token = createToken(claims, username, jwtRefreshExpirationMs);
        
        // 将生成的Token存入Redis
        storeTokenInRedis(token, username, "refresh", jwtRefreshExpirationMs);
        
        return token;
    }

    /**
     * 创建Token
     */
    private String createToken(Map<String, Object> claims, String subject, int expirationMs) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMs);
        
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(expirationDate)
                .withPayload(claims)
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    /**
     * 刷新访问Token
     */
    public String refreshAccessToken(String refreshToken) {
        try {
            // 验证刷新Token
            if (!validateJwtToken(refreshToken)) {
                throw new BusinessException(ErrorEnum.AUTH_TOKEN_INVALID);
            }

            // 检查Token是否为刷新Token
            String tokenType = extractClaim(refreshToken, jwt -> jwt.getClaim("type").asString());
            if (!"refresh".equals(tokenType)) {
                throw new BusinessException(ErrorEnum.AUTH_REFRESH_TOKEN_INVALID);
            }

            // 检查Token有效期
            Date expirationDate = getExpirationDateFromJwtToken(refreshToken);
            long expirationTime = expirationDate.getTime() - System.currentTimeMillis();
            if (expirationTime < jwtExpirationMs) {
                // 有效期短于访问Token，说明不是刷新Token
                throw new BusinessException(ErrorEnum.AUTH_REFRESH_TOKEN_INVALID);
            }

            // 获取用户名并生成新的访问Token
            String username = getUsernameFromJwtToken(refreshToken);
            
            // 生成新的访问Token，会自动存入Redis
            return generateAccessToken(username);
        } catch (TokenExpiredException e) {
            // 刷新Token已过期
            throw new BusinessException(ErrorEnum.AUTH_REFRESH_TOKEN_EXPIRED);
        } catch (JWTVerificationException e) {
            throw new BusinessException(ErrorEnum.AUTH_TOKEN_INVALID);
        }
    }
    
    /**
     * 从Redis中检查Token是否存在且有效
     */
    public boolean isTokenValidInRedis(String token) {
        try {
            Boolean exists = redisTemplate.hasKey(JWT_PREFIX + token);
            return exists != null && exists;
        } catch (Exception e) {
            logger.error("Failed to check token validity in Redis: {}", e.getMessage());
            return false;
        }
    }
    
    /**
     * 从Redis获取Token对应的用户名
     */
    public String getUsernameFromRedisToken(String token) {
        try {
            Object username = redisTemplate.opsForValue().get(JWT_PREFIX + token);
            return username != null ? username.toString() : null;
        } catch (Exception e) {
            logger.error("Failed to get username from Redis token: {}", e.getMessage());
            return null;
        }
    }
}