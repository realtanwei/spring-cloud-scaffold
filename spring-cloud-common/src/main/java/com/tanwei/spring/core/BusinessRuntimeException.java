package com.tanwei.spring.core;

import com.tanwei.spring.core.StatusCode;
import lombok.Getter;

/**
 * @author tanwei
 * @date 2023-07-13 9:09
 **/
@Getter
public class BusinessRuntimeException extends RuntimeException{

    private String code;

    public BusinessRuntimeException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
    }

    public BusinessRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }
}
