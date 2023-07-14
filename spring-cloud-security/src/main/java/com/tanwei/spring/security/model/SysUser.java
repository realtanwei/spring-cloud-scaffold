package com.tanwei.spring.security.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tanwei
 * @date 2023-07-04 15:03
 **/
@Data
@TableName(value = "sys_user")
public class SysUser implements Serializable {

    /**
     * 会员id
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（1：正常 0：停用）
     */
    private Integer status;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 用户性别（0男，1女，2未知）
     */
    private Integer gender;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户类型（0管理员，1普通用户）
     */
    private Integer userType;

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

}
