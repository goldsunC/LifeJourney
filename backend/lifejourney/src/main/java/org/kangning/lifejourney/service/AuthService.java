package org.kangning.lifejourney.service;

import org.kangning.lifejourney.dto.request.LoginRequest;
import org.kangning.lifejourney.dto.request.RegisterRequest;
import org.kangning.lifejourney.dto.request.RefreshTokenRequest;
import org.kangning.lifejourney.dto.response.AuthResponse;

/**
 * 认证服务接口
 * 定义用户注册、登录和刷新Token等认证相关功能
 */
public interface AuthService {

    /**
     * 用户注册
     * @param registerRequest 注册请求参数
     * @return 认证响应，包含用户信息和Token
     */
    AuthResponse register(RegisterRequest registerRequest);

    /**
     * 用户登录
     * @param loginRequest 登录请求参数
     * @return 认证响应，包含用户信息和Token
     */
    AuthResponse login(LoginRequest loginRequest);

    /**
     * 刷新访问Token
     * @param refreshTokenRequest 刷新Token请求参数
     * @return 认证响应，包含新的访问Token
     */
    AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    /**
     * 检查用户名是否已存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 检查邮箱是否已存在
     * @param email 邮箱
     * @return 是否存在
     */
    boolean existsByEmail(String email);
}