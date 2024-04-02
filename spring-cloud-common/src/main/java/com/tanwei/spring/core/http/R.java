package com.tanwei.spring.core.http;

import com.tanwei.spring.core.utils.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author tanwei
 * @date 2023-05-09 11:01
 **/
@Data
@AllArgsConstructor
public class R<D> {

    private String code;
    private String message;
    private boolean success;
    private D data;

    public static <D> R<D> success() {
        return success(null);
    }

    public static <D> R<D> success(D data) {
        return success(StatusCode.SUCCESS.getMessage(), data);
    }

    public static <D> R<D> success(String message, D data) {
        return build(StatusCode.SUCCESS.getCode(), message, Boolean.TRUE,data);
    }

    public static <D> R<D> error(String message) {
        return fail(message, null);
    }

    public static <D> R<D> fail(D data) {
        return fail(StatusCode.FAILED.getMessage(), data);
    }

    public static <D> R<D> fail(String message, D data) {
        return build(StatusCode.FAILED.getCode(), message, Boolean.FALSE, data);
    }

    public static <D> R<D> build(StatusCode statusCode, boolean success, D data) {
        return build(statusCode.getCode(), statusCode.getMessage(), success, data);
    }
    public static <D> R<D> build(String code, String message, boolean success, D data) {
        return new R<>(code,  message, success, data);
    }

    public D parserData(String data, Class<D> dClass) {
        return JSONUtil.parseObject(data, dClass);
    }

    @Override
    public String toString() {
        return JSONUtil.toJSONString(this);
    }
}
