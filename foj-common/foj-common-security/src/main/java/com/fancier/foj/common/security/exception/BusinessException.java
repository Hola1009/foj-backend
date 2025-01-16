package com.fancier.foj.common.security.exception;

import com.fancier.foj.common.core.constant.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private int code;
    private String message;

    public BusinessException(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public BusinessException(ResultCode code, String message) {
        this.code = code.getCode();
        this.message = message;
    }
}
