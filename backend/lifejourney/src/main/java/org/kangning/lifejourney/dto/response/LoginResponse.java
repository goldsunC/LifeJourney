package org.kangning.lifejourney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录响应DTO
 * 用于用户登录成功后的响应数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // JWT访问令牌
    private String accessToken;
    
    // 访问令牌过期时间
    private LocalDateTime accessTokenExpiresAt;
    
    // JWT刷新令牌
    private String refreshToken;
    
    // 刷新令牌过期时间
    private LocalDateTime refreshTokenExpiresAt;
    
    // 用户信息
    private Object userInfo;
    
    // 令牌类型
    private String tokenType = "Bearer";
}