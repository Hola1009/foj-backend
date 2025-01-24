package com.fancier.foj.system.domain.exam;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 竞赛题目表
 * @TableName tb_exam_question
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_exam_question")
@Data
public class ExamQuestion extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 竞赛id
     */
    private Long examId;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 题目顺序
     */
    private Integer questionOrder;

}