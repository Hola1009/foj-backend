package com.fancier.foj.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@AllArgsConstructor
public enum UserIdentity {

    ADMIN(2, "管理员"),
    ORDINARY(1, "普通用户");

    private final int value;
    private final String desc;
}
