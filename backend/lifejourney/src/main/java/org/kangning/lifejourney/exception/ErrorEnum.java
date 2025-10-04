package org.kangning.lifejourney.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类
 * 统一管理系统中的错误码和错误消息
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    
    // 系统通用错误
    SUCCESS("000000", "操作成功"),
    SYSTEM_ERROR("999999", "系统繁忙，请稍后再试"),
    PARAM_ERROR("100001", "参数校验失败"),
    DATA_NOT_FOUND("100002", "请求的数据不存在"),
    UNAUTHORIZED("100003", "未授权访问"),
    ACCESS_DENIED("100004", "权限不足"),
    REQUEST_TIMEOUT("100005", "请求超时"),
    
    // 业务相关错误
    USER_NOT_FOUND("200001", "用户不存在"),
    USER_ALREADY_EXIST("200002", "用户已存在"),
    ACCOUNT_LOCKED("200003", "账号已被锁定"),
    PASSWORD_ERROR("200004", "密码错误"),
    INVALID_TOKEN("200005", "无效的令牌"),
    TOKEN_EXPIRED("200006", "令牌已过期"),
    USER_UPDATE_FAIL("200007", "用户信息更新失败"),
    
    // 认证相关错误
    USERNAME_ALREADY_EXISTS("200101", "用户名已存在"),
    EMAIL_ALREADY_EXISTS("200102", "邮箱已存在"),
    USER_REGISTER_FAIL("200103", "用户注册失败"),
    AUTHENTICATION_FAILED("200104", "认证失败"),
    AUTH_TOKEN_INVALID("200105", "认证令牌无效"),
    AUTH_REFRESH_TOKEN_INVALID("200106", "刷新令牌无效"),
    AUTH_REFRESH_TOKEN_EXPIRED("200107", "刷新令牌已过期"),
    
    // 数据操作相关错误
    DATABASE_ERROR("300001", "数据库操作失败"),
    DATA_INTEGRITY_VIOLATION("300002", "数据完整性约束违反"),
    DUPLICATE_KEY("300003", "数据重复"),
    
    // 文件操作相关错误
    FILE_UPLOAD_ERROR("400001", "文件上传失败"),
    FILE_DOWNLOAD_ERROR("400002", "文件下载失败"),
    FILE_SIZE_EXCEED("400003", "文件大小超出限制"),
    FILE_TYPE_NOT_ALLOWED("400004", "不支持的文件类型");

    private final String code;
    private final String message;
}