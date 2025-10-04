package org.kangning.lifejourney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户DTO
 * 用于API接口中的用户信息传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 用户ID
    private Long id;
    
    // 用户名
    private String username;
    
    // 昵称
    private String nickname;
    
    // 邮箱
    private String email;
    
    // 头像URL
    private String avatar;
    
    // 简介
    private String bio;
    
    // 注册时间
    private LocalDateTime createdAt;
    
    // 更新时间
    private LocalDateTime updatedAt;
}