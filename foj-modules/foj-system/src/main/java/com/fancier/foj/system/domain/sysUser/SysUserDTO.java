package com.fancier.foj.system.domain.sysUser;

import lombok.Data;

/**
 * sys 用户登录
 */

@Data
public class SysUserDTO {
    private Long id;

    private String account;

    private String password;

    private String username;

}
