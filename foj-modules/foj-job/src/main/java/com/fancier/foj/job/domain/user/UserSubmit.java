package com.fancier.foj.job.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName tb_user_submit
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_user_submit")
@Data
public class UserSubmit extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long userId;

    private Long questionId;

    private Long examId;

    private String programType;

    private String code;

    private Integer pass;

    private Integer score;

    private String exeMessage;

    private String caseJudgeRes;

}