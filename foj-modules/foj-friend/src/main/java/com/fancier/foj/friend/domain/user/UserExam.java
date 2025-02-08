package com.fancier.foj.friend.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 * @TableName tb_user_exam
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="tb_user_exam")
@Data
public class UserExam extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 竞赛id
     */
    private Long examId;

    /**
     * 得分
     */
    private Integer score;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

}