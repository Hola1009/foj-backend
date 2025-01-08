package com.fancier.foj.common.core.domain;

import com.fancier.foj.common.core.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result  {
    private int code; // 错误码
    private String message; // 提示信息
    private Object data; // 返回数据

    public static  Result success(Object data) {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static  Result success() {
        return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static  Result failure() {
        return new Result(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage(), null);
    }

    public static  Result failure(ResultCode resultCode) {
        return new Result(resultCode.getCode(), resultCode.getMessage(), null);
    }
}
