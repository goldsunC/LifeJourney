package org.kangning.lifejourney.exception;

/**
 * 业务异常类
 * 用于表示业务逻辑执行过程中发生的异常
 */
public class BusinessException extends BaseException {

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     */
    public BusinessException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    /**
     * 构造函数，包含原始异常
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     * @param cause 原始异常
     */
    public BusinessException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }
    
    /**
     * 构造函数，使用错误枚举
     * @param errorEnum 错误枚举
     */
    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getCode(), errorEnum.getMessage());
    }
    
    /**
     * 构造函数，使用错误枚举和自定义消息
     * @param errorEnum 错误枚举
     * @param customMessage 自定义错误消息
     */
    public BusinessException(ErrorEnum errorEnum, String customMessage) {
        super(errorEnum.getCode(), customMessage);
    }
    
    /**
     * 构造函数，使用错误枚举和原始异常
     * @param errorEnum 错误枚举
     * @param cause 原始异常
     */
    public BusinessException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getCode(), errorEnum.getMessage(), cause);
    }
}