package org.kangning.lifejourney.dto.request;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 用户注册请求DTO
 */
@Data
public class RegisterRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 50, message = "用户名长度必须在4-50个字符之间")
    private String username;

    @NotBlank(message = "邮箱不能为空")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 50, message = "密码长度必须在8-50个字符之间")
    private String password;

    @Size(max = 100, message = "显示名称长度不能超过100个字符")
    private String displayName;
}