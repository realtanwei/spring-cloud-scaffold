package com.tanwei.spring.security.resource;

import com.tanwei.spring.security.model.SysMenu;
import com.tanwei.spring.security.service.SysMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tanwei
 * @date 2023-07-10 9:42
 **/
@Component
public record AccessSecurityService(SysMenuService sysMenuService) {

    /**
     * 获取系统所有资源数据
     * @return
     */
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> collect = sysMenuService.lambdaQuery()
                .eq(SysMenu::getStatus, "1")
                .list()
                .stream()
                .distinct()
                .collect(Collectors.toMap(SysMenu::getPath, sysMenu -> new SecurityConfig(sysMenu.getPerms())));
        return collect;
    }
}
