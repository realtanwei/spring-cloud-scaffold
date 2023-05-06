package com.tanwei.spring.business.producer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanwei.spring.business.producer.client.HelloClient;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * @author tanwei
 * @date 2023-05-06 15:03
 **/
@Configuration
public class WebClientConfiguration {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://www.verycd.com/")
                .build();
    }


    @Bean
    HelloClient helloClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(HelloClient.class);
    }

}
