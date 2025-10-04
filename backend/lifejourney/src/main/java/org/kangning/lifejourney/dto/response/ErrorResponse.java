package org.kangning.lifejourney.dto.response;

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
public class ErrorResponse implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // 错误码
    private String code;
    
    // 错误消息
    private String message;
    
    // 错误详情（可选）
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