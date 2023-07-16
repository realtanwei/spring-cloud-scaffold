package com.tanwei.spring.security.resource;

import com.tanwei.spring.core.StatusCode;
import com.tanwei.spring.core.BusinessRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author tanwei
 * @date 2023-07-11 17:40
 **/
@Component
@RequiredArgsConstructor
public class AccessAuthorizationManagerAdapter implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final AccessSecurityMetadataSource securityMetadataSource;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {

        Collection<ConfigAttribute> configAttributes = this.securityMetadataSource.getAttributes(authorizationContext);
        if (CollectionUtils.isEmpty(configAttributes)) {
            throw new BusinessRuntimeException(StatusCode.NOTFOUND);
        }
        return authentication.map(auth -> {
            for (ConfigAttribute configAttribute : configAttributes) {
                String needAuthority = configAttribute.getAttribute();
                for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                    if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                        return new AuthorizationDecision(true);
                    }
                }
            }
            return new AuthorizationDecision(false);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }
}
