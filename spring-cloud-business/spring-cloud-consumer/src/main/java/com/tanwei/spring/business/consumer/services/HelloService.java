package com.tanwei.spring.business.consumer.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tanwei
 * @date 2023-05-03 2:43
 **/
@FeignClient(value = "spring-cloud-producer")
public interface HelloService {

    @GetMapping(value = "hello")
    String hello();
}
