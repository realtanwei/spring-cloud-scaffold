package com.tanwei.spring.security.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanwei.spring.security.UserDetailsImpl;
import com.tanwei.spring.security.mapper.SysUserMapper;
import com.tanwei.spring.security.model.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tanwei
 * @date 2023-07-04 15:12
 **/
@Service
@RequiredArgsConstructor
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements UserDetailsService {

    private final SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.lambdaQuery().eq(SysUser::getUsername, username)
                .oneOpt().orElseThrow(() -> new UsernameNotFoundException("用户名不存在"));
        List<String> permissions = sysMenuService.selectPermsByUserId(sysUser.getId());
        return new UserDetailsImpl(sysUser, permissions);
    }
}
