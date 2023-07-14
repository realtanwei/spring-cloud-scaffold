package com.tanwei.spring.gateway.config;

import com.tanwei.spring.gateway.exception.GatewayErrorWebExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @author tanwei
 * @date 2023-07-14 16:53
 **/
@Configuration
public class GatewayExceptionConfig {

    private final ServerProperties serverProperties;

    public GatewayExceptionConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public ErrorWebExceptionHandler errorWebExceptionHandler(
            ErrorAttributes errorAttributes, WebProperties webProperties,
            ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer,
            ApplicationContext applicationContext) {
        GatewayErrorWebExceptionHandler exceptionHandler = new GatewayErrorWebExceptionHandler(errorAttributes,
                webProperties.getResources(),
                this.serverProperties.getError(), applicationContext);
        exceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }
}
