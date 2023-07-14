package com.tanwei.spring.security;

import com.tanwei.spring.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author tanwei
 * @date 2023-07-14 10:19
 **/
@Component
@RequiredArgsConstructor
public class AccessSecurityContextRepository implements ServerSecurityContextRepository {

    private final AccessAuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        String token = exchange.getRequest().getHeaders().getFirst(JwtService.TOKEN_HEADER);
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(token, null))
                .map(SecurityContextImpl::new);
    }
}
