package com.tanwei.spring.security.exception;

import lombok.Getter;

/**
 * @author tanwei
 * @date 2023-07-13 9:09
 **/
@Getter
public class BusinessRuntimeException extends RuntimeException{

    private String code;

    public BusinessRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }
}
