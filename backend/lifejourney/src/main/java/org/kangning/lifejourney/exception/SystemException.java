package org.kangning.lifejourney.exception;

/**
 * 系统异常类
 * 用于表示系统层面、技术层面的异常
 */
public class SystemException extends BaseException {

    /**
     * 构造函数
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     */
    public SystemException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    /**
     * 构造函数，包含原始异常
     * @param errorCode 错误码
     * @param errorMessage 错误消息
     * @param cause 原始异常
     */
    public SystemException(String errorCode, String errorMessage, Throwable cause) {
        super(errorCode, errorMessage, cause);
    }
}