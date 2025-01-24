package com.fancier.foj.system.domain.exam;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 竞赛表
 * @TableName tb_exam
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_exam")
@Data
public class Exam extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

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