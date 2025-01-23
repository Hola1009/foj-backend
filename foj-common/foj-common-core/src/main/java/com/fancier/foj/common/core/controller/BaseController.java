package com.fancier.foj.common.core.controller;

import cn.hutool.core.collection.CollUtil;
import com.fancier.foj.common.core.domain.vo.PageResult;
import com.fancier.foj.common.core.domain.vo.Result;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 *
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
public class BaseController {

    // 根据操作结果来返回
    public Result toResult(Boolean flag) {
        return flag ? Result.success() : Result.failure();
    }

    public PageResult toPageResult(List<?> list) {
        // 判断是否为空
        if (CollUtil.isEmpty(list)) {
            return PageResult.empty();
        }

        // 返回结果, 并获取数据总量
        return PageResult.success(list, new PageInfo<>(list).getTotal());
    }
}
