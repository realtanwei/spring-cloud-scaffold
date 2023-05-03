package com.tanwei.spring.business.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tanwei
 * @date 2023-04-28 16:24
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ProducerSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerSpringBootApplication.class, args);
    }
}
