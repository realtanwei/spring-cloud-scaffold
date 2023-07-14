package com.tanwei.spring.business.producer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @date 2023-05-03 2:40
 **/
@Slf4j
@RestController
public class HelloController {

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "hello")
    public String hello() {
        log.info("项目运行端口：{}", port);
        return "Hello, World!";
    }
}
