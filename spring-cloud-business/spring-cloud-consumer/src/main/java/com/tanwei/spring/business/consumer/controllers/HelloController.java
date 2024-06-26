package com.tanwei.spring.business.consumer.controllers;

import com.tanwei.spring.business.consumer.services.HelloService;
import com.tanwei.spring.core.http.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanwei
 * @date 2023-05-03 2:42
 **/
@RestController
@RequestMapping("/api/v1/")
public class HelloController {


    HelloService helloService;

    @Autowired
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "hello")
    public R<String> hello() {
        return R.success(helloService.hello());
    }
}
