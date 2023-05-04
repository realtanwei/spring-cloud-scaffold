package com.tanwei.spring.business.consumer.fallback;

import com.tanwei.spring.business.consumer.services.HelloService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author tanwei
 * @date 2023-05-04 22:48
 **/
@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {


    @Override
    public HelloService create(Throwable cause) {
        return () -> "error";
    }
}
