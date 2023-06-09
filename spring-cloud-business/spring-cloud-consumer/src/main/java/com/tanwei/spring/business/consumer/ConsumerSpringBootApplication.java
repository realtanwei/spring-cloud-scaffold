package com.tanwei.spring.business.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tanwei
 * @date 2023-04-28 16:24
 **/
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ConsumerSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerSpringBootApplication.class, args);
    }
}
