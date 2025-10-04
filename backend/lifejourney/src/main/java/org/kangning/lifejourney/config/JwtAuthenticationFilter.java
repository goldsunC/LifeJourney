package org.kangning.lifejourney.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.kangning.lifejourney.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT认证过滤器
 * 拦截所有请求，验证JWT Token，并设置Security上下文
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求头中获取JWT Token
            String jwt = parseJwt(request);

            if (jwt != null) {
                // 优先从Redis中验证Token是否存在且有效
                boolean isTokenValidInRedis = jwtUtils.isTokenValidInRedis(jwt);
                
                if (isTokenValidInRedis) {
                    // 如果Redis中存在有效Token，直接从Redis获取用户名
                    String username = jwtUtils.getUsernameFromRedisToken(jwt);
                    
                    if (username != null) {
                        // 加载用户详情
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        // 创建认证对象并设置到Security上下文
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        // 设置username属性到请求中，供后续控制器使用
                        request.setAttribute("username", username);
                        logger.debug("Successfully authenticated user from Redis: {}", username);
                    }
                } else {
                    // 如果Redis中不存在Token或Token无效，执行传统的Token验证流程
                    if (jwtUtils.validateJwtToken(jwt)) {
                        // 从Token中获取用户名
                        String username = jwtUtils.getUsernameFromJwtToken(jwt);

                        // 加载用户详情
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        // 创建认证对象并设置到Security上下文
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        // 设置username属性到请求中，供后续控制器使用
                        request.setAttribute("username", username);
                        logger.debug("Successfully authenticated user from JWT validation: {}", username);
                    }
                }
            }
        } catch (TokenExpiredException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            // 可以设置响应头或状态码通知客户端Token已过期
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求头中解析JWT Token
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}