package com.tanwei.spring.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanwei.spring.security.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author tanwei
 * @date 2023-07-06 15:00
 **/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
