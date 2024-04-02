package com.tanwei.spring.core.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tanwei
 * @date 2023-05-05 11:12
 **/
@Getter
@AllArgsConstructor
public enum StatusCode {

    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    PARAM_ERR("400", "参数异常"),
    UNAUTHORIZED("401", "身份认证异常"),
    FORBIDDEN("403", "权限不足"),
    NOTFOUND("404", "页面不存在");


    private final String code;
    private final String message;
}
