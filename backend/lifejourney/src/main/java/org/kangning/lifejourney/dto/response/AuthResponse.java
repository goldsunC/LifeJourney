package org.kangning.lifejourney.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 认证响应DTO
 */
@Data
public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private LocalDateTime expiresAt;
    private UserInfo userInfo;

    /**
     * 用户信息内部类
     */
    @Data
    public static class UserInfo {
        private Long id;
        private String username;
        private String email;
        private String displayName;
        private String avatarUrl;
        private String bio;
    }
}