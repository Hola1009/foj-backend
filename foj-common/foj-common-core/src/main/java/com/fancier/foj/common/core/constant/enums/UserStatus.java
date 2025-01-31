package com.fancier.foj.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@AllArgsConstructor
@Getter
public enum UserStatus {
    NORMAL(1),
    DISABLED(0),
    ;
    private final Integer value;
}
