package com.fancier.foj.system.domain.user.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class UserVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别 0: 男 1: 女
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String phone;

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
