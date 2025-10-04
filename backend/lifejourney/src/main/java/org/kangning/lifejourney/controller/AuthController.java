package org.kangning.lifejourney.controller;

import org.kangning.lifejourney.dto.request.LoginRequest;
import org.kangning.lifejourney.dto.request.RegisterRequest;
import org.kangning.lifejourney.dto.request.RefreshTokenRequest;
import org.kangning.lifejourney.dto.response.AuthResponse;
import org.kangning.lifejourney.dto.response.CommonResponse;
import org.kangning.lifejourney.service.AuthService;
import org.kangning.lifejourney.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制器
 * 处理用户注册、登录和刷新Token的HTTP请求
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 用户注册接口
     */
    @PostMapping("/register")
    public ResponseEntity<CommonResponse<AuthResponse>> register(@Validated @RequestBody RegisterRequest registerRequest) {
        logger.info("用户注册请求: {}", registerRequest.getUsername());

        // 调用服务层进行注册
        AuthResponse authResponse = authService.register(registerRequest);

        // 构建响应
        return ResponseEntity.ok(CommonResponse.success(authResponse, "用户注册成功"));
    }

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<AuthResponse>> login(@Validated @RequestBody LoginRequest loginRequest) {
        logger.info("用户登录请求: {}", loginRequest.getUsername());

        // 调用服务层进行登录
        AuthResponse authResponse = authService.login(loginRequest);

        // 构建响应
        return ResponseEntity.ok(CommonResponse.success(authResponse, "用户登录成功"));
    }

    /**
     * 刷新Token接口
     */
    @PostMapping("/refresh")
    public ResponseEntity<CommonResponse<AuthResponse>> refreshToken(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        logger.info("刷新Token请求");

        // 调用服务层刷新Token
        AuthResponse authResponse = authService.refreshToken(refreshTokenRequest);

        // 构建响应
        return ResponseEntity.ok(CommonResponse.success(authResponse, "Token刷新成功"));
    }
    
    /**
     * 用户登出接口
     */
    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<String>> logout() {
        try {
            // 获取当前认证的用户信息
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = null;
            
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                username = (String) principal;
            }
            
            if (username != null) {
                logger.info("用户登出请求: {}", username);
                
                // 从Redis中删除用户的所有Token
                jwtUtils.deleteUserTokensFromRedis(username);
                
                // 清空Security上下文
                SecurityContextHolder.clearContext();
                
                logger.info("用户登出成功: {}", username);
                return ResponseEntity.ok(CommonResponse.success("用户登出成功"));
            } else {
                logger.error("无法获取当前认证用户信息");
                return ResponseEntity.ok(CommonResponse.success("用户登出成功"));
            }
        } catch (Exception e) {
            logger.error("用户登出失败: {}", e.getMessage());
            return ResponseEntity.ok(CommonResponse.success("用户登出成功"));
        }
    }
}