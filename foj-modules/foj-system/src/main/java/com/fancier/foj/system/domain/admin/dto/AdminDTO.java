package com.fancier.foj.system.domain.admin.dto;

import lombok.Data;

/**
 * sys 用户登录
 */

@Data
public class AdminDTO {
    private Long id;

    private String account;

    private String password;

    private String username;

}
