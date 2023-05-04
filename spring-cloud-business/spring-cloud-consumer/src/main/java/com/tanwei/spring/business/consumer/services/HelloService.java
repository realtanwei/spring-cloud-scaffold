package com.tanwei.spring.business.consumer.services;

import com.tanwei.spring.business.consumer.fallback.HelloServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tanwei
 * @date 2023-05-03 2:43
 **/
@FeignClient(value = "spring-cloud-producer", fallbackFactory = HelloServiceFallbackFactory.class)
public interface HelloService {

    @GetMapping(value = "hello")
    String hello();
}
