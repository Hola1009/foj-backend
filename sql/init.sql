create database if not exists `foj_dev`;

use foj_dev;

# 管理员表
create table if not exists `sys_user` (
    `id` bigint(20) unsigned not null comment '主键',
    `account` varchar(20) not null comment '账号',
    `password` varchar(20) not null comment '密码',
    `username` varchar(20) not null comment '用户名',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 0 comment '创建者',
    `update_by` bigint(20) unsigned not null default 0 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    unique key `idx_sys_user_account` (`account`),
    primary key (`id`)
) comment='管理员表';

