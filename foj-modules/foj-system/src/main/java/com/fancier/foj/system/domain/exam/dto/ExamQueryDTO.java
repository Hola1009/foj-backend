package com.fancier.foj.system.domain.exam.dto;

import com.fancier.foj.common.core.domain.dto.BasePageQuery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Getter
@Setter
public class ExamQueryDTO extends BasePageQuery {
    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 是否发布 0: 未发布 1: 已发布
     */
    private Integer status;
}
