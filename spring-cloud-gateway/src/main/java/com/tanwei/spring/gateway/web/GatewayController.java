package com.tanwei.spring.gateway.web;

import com.tanwei.spring.core.ApiResult;
import com.tanwei.spring.gateway.model.request.AuthenticationRequest;
import com.tanwei.spring.gateway.model.response.AuthenticationResponse;
import com.tanwei.spring.gateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author tanwei
 * @date 2023-07-16 14:46
 **/
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class GatewayController {

    private final AuthService authService;

    @PostMapping("login")
    public Mono<ApiResult<AuthenticationResponse>> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authService.login(authenticationRequest);
        return Mono.just(ApiResult.success(authenticationResponse));
    }
}
