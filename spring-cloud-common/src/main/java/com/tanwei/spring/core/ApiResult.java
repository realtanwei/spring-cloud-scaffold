package com.tanwei.spring.core;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author tanwei
 * @date 2023-05-09 11:01
 **/
@Data
@AllArgsConstructor
public class ApiResult<D> {

    private String code;
    private String message;
    private boolean success;
    private D data;

    public static <D> ApiResult<D> success() {
        return success(null);
    }

    public static <D> ApiResult<D> success(D data) {
        return success(StatusCode.SUCCESS.getMessage(), data);
    }

    public static <D> ApiResult<D> success(String message, D data) {
        return build(StatusCode.SUCCESS.getCode(), message, Boolean.TRUE,data);
    }

    public static <D> ApiResult<D> error(String message) {
        return fail(message, null);
    }

    public static <D> ApiResult<D> fail(D data) {
        return fail(StatusCode.FAILED.getMessage(), data);
    }

    public static <D> ApiResult<D> fail(String message, D data) {
        return build(StatusCode.FAILED.getCode(), message, Boolean.FALSE, data);
    }

    public static <D> ApiResult<D> thin(StatusCode statusCode, boolean success,  D data) {
        return build(statusCode.getCode(), statusCode.getMessage(), success, data);
    }

    public static <D> ApiResult<D> build(String code, String message, boolean success, D data) {
        return new ApiResult<>(code,  message, success, data);
    }

    public D parserData(String data, Class<D> dClass) {
        return JSON.parseObject(data, dClass);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
