package com.fancier.foj.common.mybatis.utils;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
public class UserHolder {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long userId) {
        threadLocal.set(userId);
    }

    public static Long get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
