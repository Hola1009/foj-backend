create database if not exists `foj_dev`;

use foj_dev;

# 管理员表
create table if not exists `sys_user` (
    `id` bigint(20) unsigned not null comment '主键',
    `account` varchar(20) not null comment '账号',
    `password` char(60) not null comment '密码',
    `username` varchar(20) not null comment '用户名',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 0 comment '创建者',
    `update_by` bigint(20) unsigned not null default 0 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    unique key `idx_sys_user_account` (`account`),
    primary key (`id`)
) comment='管理员表';

# 题目表
create table if not exists `tb_question` (
    `id` bigint(20) unsigned not null comment '主键',
    `title` varchar(50) not null comment '标题',
    `difficulty` tinyint(1) not null comment '难度',
    `description` text not null comment '描述',
    `time_limit` int(11) not null comment '时间限制',
    `space_limit` int(11) not null comment '空间限制',
    `content` varchar(1000) not null comment '题目内容',
    # 使用 json 字符串来存储
    `question_case` varchar(1000) comment '题目用例',
    `default_code` varchar(500) not null comment '默认代码',
    `main_func` varchar(500) not null comment '主函数',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
) comment = '题目表';
