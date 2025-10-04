package org.kangning.lifejourney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kangning.lifejourney.exception.ErrorEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 通用响应对象
 * 统一API的返回格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    // 操作是否成功
    private boolean success;
    
    // 响应数据（成功时使用）
    private T data;
    
    // 错误信息（失败时使用）
    private ErrorResponse error;
    
    // 响应消息
    private String message;
    
    // 时间戳
    private String timestamp;
    
    // 分页信息（可选）
    private Pagination pagination;
    
    /**
     * 成功响应，带数据
     */
    public static <T> CommonResponse<T> success(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage("操作成功");
        response.setTimestamp(LocalDateTime.now().format(FORMATTER));
        return response;
    }
    
    /**
     * 成功响应，带数据和分页信息
     */
    public static <T> CommonResponse<T> success(T data, Pagination pagination) {
        CommonResponse<T> response = success(data);
        response.setPagination(pagination);
        return response;
    }
    
    /**
     * 成功响应，自定义消息
     */
    public static <T> CommonResponse<T> success(T data, String message) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(true);
        response.setData(data);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().format(FORMATTER));
        return response;
    }
    
    /**
     * 成功响应，不带数据
     */
    public static <T> CommonResponse<T> success() {
        return success(null);
    }
    
    /**
     * 失败响应
     */
    public static <T> CommonResponse<T> fail(String code, String message) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().format(FORMATTER));
        // 设置错误对象
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setMessage(message);
        response.setError(errorResponse);
        return response;
    }
    
    /**
     * 失败响应（使用ErrorEnum）
     */
    public static <T> CommonResponse<T> fail(ErrorEnum errorEnum) {
        return fail(errorEnum.getCode(), errorEnum.getMessage());
    }
    
    /**
     * 失败响应（带错误详情）
     */
    public static <T> CommonResponse<T> fail(String code, String message, Object details) {
        CommonResponse<T> response = fail(code, message);
        response.getError().setDetails(details);
        return response;
    }
}