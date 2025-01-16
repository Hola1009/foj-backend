package com.fancier.foj.common.core.controller;

import com.fancier.foj.common.core.domain.Result;

/**
 *
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
public class BaseController {

    // 根据操作结果来返回
    public Result toResult(Boolean flag) {
        return flag ? Result.success() : Result.failure();
    }
}
