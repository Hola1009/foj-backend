package com.fancier.foj.system.domain.exam;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 竞赛题目表
 * @TableName tb_exam_question
 */
@TableName(value ="tb_exam_question")
@Data
@Builder
public class ExamQuestion  {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
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