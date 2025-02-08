package com.fancier.foj.common.core.domain.vo;

import cn.hutool.core.collection.CollectionUtil;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Setter
@Getter
@Builder
public class PageResult {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    private int code; // 错误码

    private String message; // 提示信息

    public static PageResult empty() {
        return PageResult.builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .rows(Collections.emptyList())
                .total(0)
                .build();
    }

    public static PageResult success(List<?> rows, long total) {
        if (CollectionUtil.isEmpty(rows)) {
            return empty();
        }
        return PageResult.builder()
                .code(ResultCode.SUCCESS.getCode())
                .message(ResultCode.SUCCESS.getMessage())
                .rows(rows)
                .total(total)
                .build();
    }

}
