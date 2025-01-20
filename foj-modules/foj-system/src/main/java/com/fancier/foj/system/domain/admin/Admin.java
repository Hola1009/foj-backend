package com.fancier.foj.system.domain.admin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fancier.foj.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName sys_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user")
@Data
public class Admin extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String account;

    private String password;

    private String username;

}