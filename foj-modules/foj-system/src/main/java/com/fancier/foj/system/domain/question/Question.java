package com.fancier.foj.system.domain.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName tb_question
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_question")
@Data
public class Question extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String title;

    private Integer difficulty;

    private Integer timeLimit;

    private Integer spaceLimit;

    private String content;

    private String questionCase;

    private String defaultCode;

    private String mainFunc;

}