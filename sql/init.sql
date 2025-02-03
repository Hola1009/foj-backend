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

create table if not exists `tb_exam` (
    `id` bigint(20) unsigned not null comment '主键',
    `title` varchar(50) not null comment '标题',
    `start_time` datetime not null comment '开始时间',
    `end_time` datetime not null comment '结束时间',
    `status` tinyint(1) not null default 0 comment '是否发布 0: 未发布 1: 已发布',


    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
) comment = '竞赛表';


create table if not exists `tb_exam_question` (
    `id` bigint(20) unsigned not null comment '主键',
    `exam_id` bigint(20) unsigned not null comment '竞赛id',
    `question_id` bigint(20) unsigned not null comment '题目id',
    `question_order` int(11) not null comment '题目顺序',
    primary key (`id`)
) comment = '竞赛题目表';

create table if not exists `tb_user` (
    `id` bigint(20) unsigned not null comment '主键',
    `username` varchar(50) not null comment '用户名',
    `avatar` varchar(100) comment '头像',
    `sex` tinyint(1) not null default 0 comment '性别 0: 男 1: 女',
    `phone` varchar(20) comment '手机号',
    `code` varchar(20) comment '验证码',
    `email` varchar(50) comment '邮箱地址',
    `wechat` varchar(50) comment '微信号',
    `school` varchar(50) comment '学校',
    `major` varchar(50) comment '专业',
    `introduce` varchar(500) comment '个人介绍',
    `status` tinyint(1) not null default 0 comment '状态 0: 正常 1: 禁用',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
) comment = '普通用户表';

create table if not exists `tb_user_exam` (
    `id` bigint(20) unsigned not null comment '主键',
    `user_id` bigint(20) unsigned not null comment '用户id',
    `exam_id` bigint(20) unsigned not null comment '竞赛id',
    `score` int(11) not null default 0 comment '得分',
    `submit_time` datetime not null default current_timestamp comment '提交时间',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
);

create table if not exists `tb_user_submit`(
    `id` bigint(20) unsigned not null comment '主键',
    `user_id` bigint(20) unsigned not null comment '用户id',
    `question_id` bigint(20) unsigned not null comment '题目id',
    `exam_id` bigint(20) unsigned comment '竞赛id',
    `program_type` varchar(50) not null comment '编程语言',
    `code` text not null comment '代码',
    `pass` tinyint(1) not null default 0 comment '是否通过 0: 未通过 1: 通过',
    `score` int(11) not null default 0 comment '得分',
    `exe_message` varchar(500) comment '执行信息',
    `case_judge_res` varchar(500) comment '测试用例结果',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
);

create table if not exists `tb_message`(
    `id` bigint(20) unsigned not null comment '主键',
    `text_id` bigint(20) unsigned not null comment '消息id',
    `send_id` bigint(20) unsigned not null comment '发送者id',
    `rec_id` bigint(20) unsigned not null comment '接收者id',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
);

create table if not exists `tb_message_text`(
    `id` bigint(20) unsigned not null comment '主键',
    `message_title` varchar(50) not null comment '消息标题',
    `message_content` varchar(500) not null comment '消息内容',

    `create_time` datetime not null default current_timestamp comment '创建时间',
    `update_time` datetime not null default current_timestamp on update current_timestamp comment '更新时间',

    `create_by` bigint(20) unsigned not null default 1009 comment '创建者',
    `update_by` bigint(20) unsigned not null default 1009 comment '更新者',

    `is_deleted` tinyint(1) not null default 0 comment '是否删除',
    primary key (`id`)
);