package com.fancier.foj.friend.domain.user.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class UserVO {
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
