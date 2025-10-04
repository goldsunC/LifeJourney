package org.kangning.lifejourney.exception;

import org.kangning.lifejourney.dto.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 统一处理系统中的所有异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<?>> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn("Business exception occurred: {}. Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        CommonResponse<?> response = CommonResponse.fail(e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<CommonResponse<?>> handleSystemException(SystemException e, HttpServletRequest request) {
        log.error("System exception occurred: {}. Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        CommonResponse<?> response = CommonResponse.fail(e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("Method argument validation failed. Request URI: {}", request.getRequestURI(), e);
        
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
                
        CommonResponse<?> response = CommonResponse.fail(ErrorEnum.PARAM_ERROR.getCode(), errorMsg);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理绑定异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResponse<?>> handleBindException(BindException e, HttpServletRequest request) {
        log.warn("Bind exception occurred. Request URI: {}", request.getRequestURI(), e);
        
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
                
        CommonResponse<?> response = CommonResponse.fail(ErrorEnum.PARAM_ERROR.getCode(), errorMsg);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CommonResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        log.warn("Resource not found. Request URI: {}", request.getRequestURI(), e);
        CommonResponse<?> response = CommonResponse.fail("404", "请求的资源不存在");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * 处理不支持的HTTP方法异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CommonResponse<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("HTTP method not supported. Request URI: {}", request.getRequestURI(), e);
        CommonResponse<?> response = CommonResponse.fail("405", "不支持的HTTP方法");
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<?>> handleException(Exception e, HttpServletRequest request) {
        log.error("Unexpected exception occurred: {}. Request URI: {}", e.getMessage(), request.getRequestURI(), e);
        CommonResponse<?> response = CommonResponse.fail(ErrorEnum.SYSTEM_ERROR.getCode(), ErrorEnum.SYSTEM_ERROR.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}