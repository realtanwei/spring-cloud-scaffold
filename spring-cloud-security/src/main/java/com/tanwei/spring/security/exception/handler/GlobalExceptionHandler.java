package com.tanwei.spring.security.exception.handler;

import com.tanwei.spring.core.ApiResult;
import com.tanwei.spring.security.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tanwei
 * @date 2023-07-13 9:12
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessRuntimeException.class)
    public ApiResult<Void> businessExceptionError(BusinessRuntimeException e) {
        log.error("业务异常", e);
        if (e.getCode() != null) {
            return ApiResult.build(e.getCode(), e.getMessage(), false, null);
        }
        return ApiResult.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ApiResult<Void> exceptionError(Exception e) {
        log.error("系统异常", e);
        return ApiResult.error(e.getMessage());
    }
}
