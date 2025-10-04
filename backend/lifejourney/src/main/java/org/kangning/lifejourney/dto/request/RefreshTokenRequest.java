package org.kangning.lifejourney.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * 刷新Token请求DTO
 */
@Data
@Schema(description = "刷新Token请求参数")
public class RefreshTokenRequest {

    @Schema(description = "刷新Token字符串", required = true, example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMTIzIn0...")
    @NotBlank(message = "刷新Token不能为空")
    private String refreshToken;
}