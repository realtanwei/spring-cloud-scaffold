package com.tanwei.spring.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tanwei
 * @date 2023-05-05 11:12
 **/
@Getter
@AllArgsConstructor
public enum StatusCode {

    SUCCESS("V200200", "操作成功"),
    FAILED("V500500", "操作失败");

    private final String code;
    private final String message;
}
