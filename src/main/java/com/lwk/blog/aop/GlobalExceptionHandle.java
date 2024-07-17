package com.lwk.blog.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lwk
 * @date 2024-07-17 20:26:24
 * @deprecated 全局响应异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    /**
     * 全局异常处理
     *
     * @param e 异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error("全局异常处理", e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
