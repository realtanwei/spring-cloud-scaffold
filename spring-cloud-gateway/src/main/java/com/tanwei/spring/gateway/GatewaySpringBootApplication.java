package com.tanwei.spring.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author tanwei
 * @date 2023-05-08 10:14
 **/
@SpringBootApplication(scanBasePackages = {"com.tanwei.spring.gateway", "com.tanwei.spring.security"})
@EnableDiscoveryClient
@MapperScan(basePackages = "com.tanwei.spring.security")
public class GatewaySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaySpringBootApplication.class, args);
    }
}
