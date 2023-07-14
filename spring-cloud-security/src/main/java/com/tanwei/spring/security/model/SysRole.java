package com.tanwei.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tanwei
 * @date 2023-07-04 15:56
 **/
@Data
@TableName(value = "sys_role")
public class SysRole implements Serializable {

    @TableId
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 创建人
     */
    private String createBy;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;
}
