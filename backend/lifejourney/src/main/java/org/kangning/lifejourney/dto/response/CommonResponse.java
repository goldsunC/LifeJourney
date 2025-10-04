package org.kangning.lifejourney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用响应对象
 * 统一API的返回格式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // 响应状态码：000000表示成功，其他表示失败
    private String code;
    
    // 响应消息
    private String message;
    
    // 响应数据
    private T data;
    
    /**
     * 成功响应，带数据
     */
    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>("000000", "操作成功", data);
    }
    
    /**
     * 成功响应，不带数据
     */
    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>("000000", "操作成功", null);
    }
    
    /**
     * 失败响应
     */
    public static <T> CommonResponse<T> fail(String code, String message) {
        return new CommonResponse<>(code, message, null);
    }
    
    /**
     * 判断响应是否成功
     */
    public boolean isSuccess() {
        return "000000".equals(this.code);
    }
}