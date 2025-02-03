package com.fancier.foj.job.domain.message;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
 * @TableName tb_message
 */
@TableName(value ="tb_message")
@Getter
@Setter
public class Message extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 消息id
     */
    private Long textId;

    /**
     * 发送者id
     */
    private Long sendId;

    /**
     * 接收者id
     */
    private Long recId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}