package com.tanwei.spring.security;

import com.tanwei.spring.core.StatusCode;
import com.tanwei.spring.core.BusinessRuntimeException;
import com.tanwei.spring.security.service.JwtService;
import com.tanwei.spring.security.service.SysUserService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * @author tanwei
 * @date 2023-07-14 10:17
 **/
@Component
@RequiredArgsConstructor
public class AccessAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtService jwtService;
    private final SysUserService sysUserService;
    
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        String token = authentication.getPrincipal().toString();
        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (JwtException exception) {
            throw new BusinessRuntimeException(StatusCode.UNAUTHORIZED.getCode(), "登录凭证错误或无效!");
        }

        if (StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = sysUserService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    // 用户凭证
                    null,
                    userDetails.getAuthorities());

            return Mono.just(authenticationToken);
        }

        return Mono.empty();
    }
}
