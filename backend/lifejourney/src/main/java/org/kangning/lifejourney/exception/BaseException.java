package org.kangning.lifejourney.exception;

import lombok.Getter;

/**
 * 自定义异常基类
 * 所有业务异常都应继承此类
 */
@Getter
public class BaseException extends RuntimeException {
    
    private final String errorCode;
    private final String errorMessage;

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     */
    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 构造函数，包含原始异常
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     * @param cause 原始异常
     */
    public BaseException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}