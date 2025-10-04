package org.kangning.lifejourney.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 用户注册请求DTO
 */
@Data
@Schema(description = "用户注册请求参数")
public class RegisterRequest {

    @Schema(description = "用户名", required = true, minLength = 4, maxLength = 50, example = "user123")
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 50, message = "用户名长度必须在4-50个字符之间")
    private String username;

    @Schema(description = "邮箱地址", required = true, maxLength = 100, example = "user@example.com")
    @NotBlank(message = "邮箱不能为空")
    @Size(max = 100, message = "邮箱长度不能超过100个字符")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "用户密码", required = true, minLength = 8, maxLength = 50, example = "Password123!")
    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 50, message = "密码长度必须在8-50个字符之间")
    private String password;

    @Schema(description = "用户显示名称", maxLength = 100, example = "用户123")
    @Size(max = 100, message = "显示名称长度不能超过100个字符")
    private String displayName;
}