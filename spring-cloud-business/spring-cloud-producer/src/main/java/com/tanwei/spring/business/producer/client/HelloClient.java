package com.tanwei.spring.business.producer.client;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @author tanwei
 * @date 2023-05-06 15:06
 **/
@HttpExchange(url = "statics", accept = "text/plain", contentType = "application/x-javascript")
public interface HelloClient {

    @GetExchange("title.saying")
    String saying();
}
