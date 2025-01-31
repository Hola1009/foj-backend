package com.fancier.foj.system.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 普通用户表
 * @TableName tb_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_user")
@Data
public class User extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别 0: 男 1: 女
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 学校
     */
    private String school;

    /**
     * 专业
     */
    private String major;

    /**
     * 个人介绍
     */
    private String introduce;

    /**
     * 状态 0: 正常 1: 禁用
     */
    private Integer status;
}