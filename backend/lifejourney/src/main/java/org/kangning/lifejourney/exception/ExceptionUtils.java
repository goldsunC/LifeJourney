package org.kangning.lifejourney.exception;

/**
 * 异常抛出工具类
 * 用于简化异常的抛出操作
 */
public class ExceptionUtils {
    
    /**
     * 抛出业务异常
     */
    public static void throwBusinessException(String errorCode, String errorMessage) {
        throw new BusinessException(errorCode, errorMessage);
    }
    
    /**
     * 抛出业务异常（使用ErrorEnum）
     */
    public static void throwBusinessException(ErrorEnum errorEnum) {
        throw new BusinessException(errorEnum.getCode(), errorEnum.getMessage());
    }
    
    /**
     * 抛出业务异常（带原始异常）
     */
    public static void throwBusinessException(String errorCode, String errorMessage, Throwable cause) {
        throw new BusinessException(errorCode, errorMessage, cause);
    }
    
    /**
     * 抛出系统异常
     */
    public static void throwSystemException(String errorCode, String errorMessage) {
        throw new SystemException(errorCode, errorMessage);
    }
    
    /**
     * 抛出系统异常（使用ErrorEnum）
     */
    public static void throwSystemException(ErrorEnum errorEnum) {
        throw new SystemException(errorEnum.getCode(), errorEnum.getMessage());
    }
    
    /**
     * 抛出系统异常（带原始异常）
     */
    public static void throwSystemException(String errorCode, String errorMessage, Throwable cause) {
        throw new SystemException(errorCode, errorMessage, cause);
    }
    
    /**
     * 如果条件为真，则抛出业务异常
     */
    public static void throwBusinessExceptionIf(boolean condition, String errorCode, String errorMessage) {
        if (condition) {
            throwBusinessException(errorCode, errorMessage);
        }
    }
    
    /**
     * 如果条件为真，则抛出业务异常（使用ErrorEnum）
     */
    public static void throwBusinessExceptionIf(boolean condition, ErrorEnum errorEnum) {
        if (condition) {
            throwBusinessException(errorEnum);
        }
    }
    
    /**
     * 如果条件为真，则抛出系统异常
     */
    public static void throwSystemExceptionIf(boolean condition, String errorCode, String errorMessage) {
        if (condition) {
            throwSystemException(errorCode, errorMessage);
        }
    }
    
    /**
     * 如果条件为真，则抛出系统异常（使用ErrorEnum）
     */
    public static void throwSystemExceptionIf(boolean condition, ErrorEnum errorEnum) {
        if (condition) {
            throwSystemException(errorEnum);
        }
    }
}