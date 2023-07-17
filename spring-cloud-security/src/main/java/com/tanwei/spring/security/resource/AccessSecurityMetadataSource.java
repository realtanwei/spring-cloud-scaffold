package com.tanwei.spring.security.resource;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author tanwei
 * @date 2023-07-12 10:09
 **/
@Component
public class AccessSecurityMetadataSource implements SecurityMetadataSource {
    private static Map<String, ConfigAttribute> configAttributeMap = null;

    AccessSecurityService accessSecurityService;


    @Autowired
    public void serAccessSecurityService(AccessSecurityService accessSecurityService) {
        this.accessSecurityService = accessSecurityService;
    }

    /**
     * 加载资源数据
     */
    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = accessSecurityService.loadDataSource();
    }

    /**
     * 清除资源数据
     */
    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((AuthorizationContext) object).getExchange().getRequest().getPath().value();
        PathMatcher pathMatcher = new AntPathMatcher();
        //获取访问该路径所需资源
        for (String pattern : configAttributeMap.keySet()) {
            if (pathMatcher.match(pattern, url)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
