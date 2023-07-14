package com.tanwei.spring.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tanwei.spring.security.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author tanwei
 * @date 2023-07-11 10:23
 **/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectPermsByUserId(Long id);
}
