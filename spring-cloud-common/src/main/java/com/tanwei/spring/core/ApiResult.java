package com.tanwei.spring.core;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author tanwei
 * @date 2023-05-05 11:01
 **/
@Data
@AllArgsConstructor
public class ApiResult<D> {

    private String code;
    private String message;
    private D data;

    public static <D> ApiResult<D> success(D data) {
        return build(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), data);
    }

    public static <D> ApiResult<D> success(String message, D data) {
        return build(StatusCode.SUCCESS.getCode(), message, data);
    }

    public static <D> ApiResult<D> fail(D data) {
        return build(StatusCode.FAILED.getCode(), StatusCode.FAILED.getMessage(), data);
    }

    public static <D> ApiResult<D> fail(String message, D data) {
        return build(StatusCode.FAILED.getCode(), message, data);
    }

    public static <D> ApiResult<D> thin(StatusCode statusCode, D data) {
        return build(statusCode.getCode(), statusCode.getMessage(), data);
    }

    public static <D> ApiResult<D> build(String code, String message, D data) {
        return new ApiResult<>(code, message, data);
    }

    public D parserData(String data, Class<D> dClass) {
        return JSON.parseObject(data, dClass);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
