package com.fancier.foj.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    //操作成功
    SUCCESS (1000, "操作成功"),
    //服务器内部错误，友好提⽰
    ERROR (2000, "服务繁忙请稍后重试"),
    //操作失败，但是服务器不存在异常
    FAILED (3000, "操作失败"),
    FAILED_UNAUTHORIZED (3001, "未授权"),
    FAILED_PARAMS_VALIDATE (3002, "参数校验失败"),
    FAILED_NOT_EXISTS (3003, "资源不存在"),
    FAILED_ALREADY_EXISTS (3004, "资源已存在"),
    FILED_USER_EXISTS (3101, "⽤⼾已存在"),
    FAILED_USER_NOT_EXISTS (3102, "⽤⼾不存在"),
    FAILED_LOGIN (3103, "⽤⼾名或密码错误"),
    FAILED_USER_BANNED (3104, "您已被列⼊⿊名单, 请联系管理员."),
    EXAM_START_TIME_BEFORE_CURRENT_TIME(3201, "开始时间不能早于当前时间"),
    EXAM_START_TIME_AFTER_END_TIME(3202, "开始晚于结束时间"),
    FAILED_EXAM_IN_PROGRESS(3203, "比赛正在进行中"),
    FAILED_STATUS_NOT_EXISTS(3204, "状态不存在"),
    FAILED_USER_PHONE(3205, "你输入的手机号有误"),
    FAILED_TIME_LIMIT(3207, "当天请求次数已达到上限"),
    FAILED_FREQUENT(3208, "操作频繁，请稍后重试"),
    FAILED_INVALID_CODE(3209, "无效验证码"),
    EXAM_STARTED(3210, "竞赛已经开始，无法进行操作"),
    USER_EXAM_HAS_ENTER(3211, "用户已经报过名，无需重复报名"),

    ;

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态描述
     */
    private final String message;
}
