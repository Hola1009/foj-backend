package com.fancier.foj.job.domain.message;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @TableName tb_message_text
 */
@TableName(value ="tb_message_text")
@Getter
@Setter
public class MessageText extends BaseEntity {
    private Long id;

    private String messageTitle;

    private String messageContent;
}