package com.tanwei.spring.business.producer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @date 2023-05-03 2:40
 **/
@RestController
public class HelloController {

    @GetMapping(value = "hello")
    public String hello() {
        int i = 1 / 0;
        return "Hello, World!";
    }
}
