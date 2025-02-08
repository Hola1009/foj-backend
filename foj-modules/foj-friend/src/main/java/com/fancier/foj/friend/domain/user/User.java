package com.fancier.foj.friend.domain.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @TableName tb_user
 */
@TableName(value ="tb_user")
@Getter
@Setter
public class User extends BaseEntity {
    private Long id;

    private String username;

    private String avatar;

    private Integer sex;

    private String phone;

    private String code;

    private String email;

    private String wechat;

    private String school;

    private String major;

    private String introduce;

    private Integer status;
}