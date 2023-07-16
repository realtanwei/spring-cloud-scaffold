package com.tanwei.spring.security.exception;

import com.alibaba.fastjson.JSON;
import com.tanwei.spring.core.ApiResult;
import com.tanwei.spring.core.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 权限异常捕获
 *
 * @author tanwei
 * @date 2023-07-13 8:21
 **/
@Slf4j
@Component
public class AccessDeniedHandlerImpl implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        ApiResult<Void> apiResult = ApiResult.build(StatusCode.FORBIDDEN, false, null);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONBytes(apiResult));
        return response.writeWith(Mono.just(dataBuffer));
    }
}
