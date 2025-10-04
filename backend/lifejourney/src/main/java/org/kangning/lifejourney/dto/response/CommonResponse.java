package org.kangning.lifejourney.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "通用响应对象")
public class CommonResponse<T> implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    @Schema(description = "操作是否成功", example = "true")
    private boolean success;
    
    @Schema(description = "响应数据")
    private T data;
    
    @Schema(description = "错误信息")
    private ErrorResponse error;
    
    @Schema(description = "响应消息", example = "操作成功")
    private String message;
    
    @Schema(description = "响应时间戳", example = "2023-01-01T12:00:00")
    private String timestamp;
    
    @Schema(description = "分页信息")
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