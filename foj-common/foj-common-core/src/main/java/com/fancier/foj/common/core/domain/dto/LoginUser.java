package com.fancier.foj.common.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private String username;
    private Integer identity; // 1 表示普通用户, 2 表示 管理员用户


}
