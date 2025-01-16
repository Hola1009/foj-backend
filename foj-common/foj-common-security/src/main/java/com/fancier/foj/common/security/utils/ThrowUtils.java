package com.fancier.foj.common.security.utils;

import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.security.exception.BusinessException;


/**
 * 抛异常工具类
 *
 * @author <a href="https://github.com/hola1009">fancier</a>
 * 
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition the condition
     * @param runtimeException runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition the condition
     * @param errorCode errorCode
     */
    public static void throwIf(boolean condition, ResultCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition condition
     * @param errorCode errorCode
     * @param message message
     */
    public static void throwIf(boolean condition, ResultCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}