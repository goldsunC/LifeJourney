package org.kangning.lifejourney.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 认证响应DTO
 */
@Data
@Schema(description = "认证响应数据")
public class AuthResponse {

    @Schema(description = "访问令牌", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzLCJpYXQiOjE3Mjg4MTYxNDQsImV4cCI6MTcyODkwMjU0NH0...")
    private String accessToken;
    
    @Schema(description = "刷新令牌", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzLCJyZWZyZXNoIjoidHJ1ZSJ9...")
    private String refreshToken;
    
    @Schema(description = "令牌类型", example = "Bearer")
    private String tokenType = "Bearer";
    
    @Schema(description = "令牌过期时间")
    private LocalDateTime expiresAt;
    
    @Schema(description = "用户基本信息")
    private UserInfo userInfo;

    /**
     * 用户信息内部类
     */
    @Data
    @Schema(description = "用户基本信息")
    public static class UserInfo {
        
        @Schema(description = "用户ID", example = "1")
        private Long id;
        
        @Schema(description = "用户名", example = "user123")
        private String username;
        
        @Schema(description = "电子邮箱", example = "user@example.com")
        private String email;
        
        @Schema(description = "显示名称", example = "用户123")
        private String displayName;
        
        @Schema(description = "头像URL", example = "/uploads/avatars/user123.png")
        private String avatarUrl;
        
        @Schema(description = "用户简介")
        private String bio;
    }
}