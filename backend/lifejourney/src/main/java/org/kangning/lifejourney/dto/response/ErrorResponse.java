package org.kangning.lifejourney.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 错误响应对象
 * 用于API失败响应中的错误详情
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "错误响应信息")
public class ErrorResponse implements Serializable {    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "错误代码", example = "40001")
    private String code;
    
    @Schema(description = "错误描述消息", example = "用户名或密码错误")
    private String message;
    
    @Schema(description = "错误详细信息")
    private Object details;
    
    /**
     * 创建错误响应
     * @param code 错误码
     * @param message 错误消息
     * @return 错误响应对象
     */
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, null);
    }
    
    /**
     * 创建错误响应（带详情）
     * @param code 错误码
     * @param message 错误消息
     * @param details 错误详情
     * @return 错误响应对象
     */
    public static ErrorResponse of(String code, String message, Object details) {
        return new ErrorResponse(code, message, details);
    }
}