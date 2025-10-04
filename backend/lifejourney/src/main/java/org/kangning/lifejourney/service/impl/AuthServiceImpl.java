package org.kangning.lifejourney.service.impl;

import org.kangning.lifejourney.dto.request.LoginRequest;
import org.kangning.lifejourney.dto.request.RegisterRequest;
import org.kangning.lifejourney.dto.request.RefreshTokenRequest;
import org.kangning.lifejourney.dto.response.AuthResponse;
import org.kangning.lifejourney.entity.User;
import org.kangning.lifejourney.exception.BusinessException;
import org.kangning.lifejourney.exception.ErrorEnum;
import org.kangning.lifejourney.mapper.UserMapper;
import org.kangning.lifejourney.service.AuthService;
import org.kangning.lifejourney.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 认证服务实现类
 * 实现用户注册、登录和刷新Token等认证相关功能
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        logger.info("用户注册请求: {}", registerRequest.getUsername());

        // 检查用户名是否已存在
        if (existsByUsername(registerRequest.getUsername())) {
            throw new BusinessException(ErrorEnum.USERNAME_ALREADY_EXISTS);
        }

        // 检查邮箱是否已存在
        if (existsByEmail(registerRequest.getEmail())) {
            throw new BusinessException(ErrorEnum.EMAIL_ALREADY_EXISTS);
        }

        // 创建新用户
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setDisplayName(registerRequest.getDisplayName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // 保存用户
        int result = userMapper.insert(user);
        if (result != 1) {
            throw new BusinessException(ErrorEnum.USER_REGISTER_FAIL);
        }

        logger.info("用户注册成功: {}", registerRequest.getUsername());

        // 生成Token
        return generateAuthResponse(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        logger.info("用户登录请求: {}", loginRequest.getUsername());

        try {
            // 认证用户
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 查询用户信息
            User user = userMapper.selectByUsername(loginRequest.getUsername());
            if (user == null) {
                throw new BusinessException(ErrorEnum.USER_NOT_FOUND);
            }

            logger.info("用户登录成功: {}", loginRequest.getUsername());

            // 生成Token
            return generateAuthResponse(user);
        } catch (Exception e) {
            logger.error("用户登录失败: {}, 错误: {}", loginRequest.getUsername(), e.getMessage());
            throw new BusinessException(ErrorEnum.AUTHENTICATION_FAILED, "用户名或密码错误");
        }
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        logger.info("刷新Token请求");

        try {
            // 使用JwtUtils刷新Token
            String newAccessToken = jwtUtils.refreshAccessToken(refreshTokenRequest.getRefreshToken());

            // 从新的访问Token中获取用户名
            String username = jwtUtils.getUsernameFromJwtToken(newAccessToken);

            // 查询用户信息
            User user = userMapper.selectByUsername(username);
            if (user == null) {
                throw new BusinessException(ErrorEnum.USER_NOT_FOUND);
            }

            // 创建认证响应
            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(newAccessToken);
            authResponse.setRefreshToken(refreshTokenRequest.getRefreshToken());
            authResponse.setTokenType("Bearer");
            
            // 设置过期时间
            Date expirationDate = jwtUtils.getExpirationDateFromJwtToken(newAccessToken);
            authResponse.setExpiresAt(expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            // 设置用户信息
            authResponse.setUserInfo(buildUserInfo(user));

            logger.info("Token刷新成功: {}", username);

            return authResponse;
        } catch (BusinessException e) {
            logger.error("Token刷新失败: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Token刷新失败: {}", e.getMessage());
            throw new BusinessException(ErrorEnum.AUTH_TOKEN_INVALID);
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMapper.existsByEmail(email);
    }

    /**
     * 生成认证响应
     */
    private AuthResponse generateAuthResponse(User user) {
        // 生成Token
        String accessToken = jwtUtils.generateAccessToken(user.getUsername());
        String refreshToken = jwtUtils.generateRefreshToken(user.getUsername());

        // 创建认证响应
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(refreshToken);
        authResponse.setTokenType("Bearer");
        
        // 设置过期时间
        Date expirationDate = jwtUtils.getExpirationDateFromJwtToken(accessToken);
        authResponse.setExpiresAt(expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        // 设置用户信息
        authResponse.setUserInfo(buildUserInfo(user));

        return authResponse;
    }

    /**
     * 构建用户信息
     */
    private AuthResponse.UserInfo buildUserInfo(User user) {
        AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setEmail(user.getEmail());
        userInfo.setDisplayName(user.getDisplayName());
        userInfo.setAvatarUrl(user.getAvatarUrl());
        userInfo.setBio(user.getBio());
        return userInfo;
    }
}