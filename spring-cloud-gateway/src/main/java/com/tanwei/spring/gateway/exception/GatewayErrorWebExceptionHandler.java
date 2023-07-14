package com.tanwei.spring.gateway.exception;

import com.tanwei.spring.security.exception.BusinessRuntimeException;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;


/**
 * @author tanwei
 * @date 2023-07-14 16:22
 **/
public class GatewayErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {


    public GatewayErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable throwable = super.getError(request);
        String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        if (throwable instanceof ResponseStatusException statusException) {
            code = String.valueOf(statusException.getStatusCode().value());
            message = statusException.getMessage();
        } else if (throwable instanceof BusinessRuntimeException exception) {
            code = exception.getCode();
            message = exception.getMessage();
        } else {
            message = throwable.getMessage();
        }

        Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put("code", code);
        errorAttributes.put("message", message);
        errorAttributes.put("data", null);
        errorAttributes.put("success", false);

        return errorAttributes;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return super.getRoutingFunction(errorAttributes);
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        // Object code = errorAttributes.get("code");
        return 200;
    }
}
