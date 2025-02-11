package com.fancier.foj.common.security.service;

import cn.hutool.core.util.StrUtil;
import com.fancier.foj.common.core.constant.CacheConstants;
import com.fancier.foj.common.core.constant.JwtConstants;
import com.fancier.foj.common.core.constant.enums.ResultCode;
import com.fancier.foj.common.core.domain.dto.LoginUser;
import com.fancier.foj.common.core.utils.JwtUtils;
import com.fancier.foj.common.redis.service.RedisService;
import com.fancier.foj.common.security.exception.BusinessException;
import com.fancier.foj.common.security.utils.ThrowUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Service
@RefreshScope
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.secret}")
    private String secret; // 盐值

    private final RedisService redisService;


    /**
     * 生成用户登录 token
     */
    public String createToken(Long userId, String username, Integer identity) {
        // 创建 token
        HashMap<String, Object> claims = new HashMap<>();
        // 放入用户信息
        claims.put(JwtConstants.LOGIN_USER_ID, userId);
        String token = JwtUtils.createToken(claims, secret);

        // 1 表示普通用户, 2 表示 管理员用
        LoginUser loginUser = new LoginUser(username, identity);

        // 将用户登录信息存入 redis, 过期时间为 720 分钟
        // 以用户唯一标识作为 key , 因为 userId 是根据雪花算法生成的, 所以可以作为唯一 id
        String key = CacheConstants.LOGIN_TOKEN_PREFIX + userId;
        redisService.setCacheObject(key, loginUser, CacheConstants.EXP, TimeUnit.MINUTES);

        return token;
    }

    public void extendToken(String token) {

        String userId = JwtUtils.getUserId(token, secret);
        String key = CacheConstants.LOGIN_TOKEN_PREFIX + userId;

        Long expire = redisService.getExpire(key, TimeUnit.MINUTES);
        if (Objects.nonNull(expire) && expire < CacheConstants.REFRESH_TIME) {
            redisService.expire(key, CacheConstants.EXP, TimeUnit.MINUTES);
        }
    }

    /**
     * 解析 token 获取用户信息
     */
    public LoginUser getUserinfo(String token) {

        String key = getTokenKey(token);

        return redisService.getCacheObject(key, LoginUser.class);
    }

    public Boolean removeLoginUser(String token) {

        String key = getTokenKey(token);

        return redisService.deleteObject(key);
    }

    private String getTokenKey(String token) {
        String userId = JwtUtils.getUserId(token, secret);

        ThrowUtils.throwIf(StrUtil.isBlankIfStr(userId),
                new BusinessException(ResultCode.FAILED_USER_NOT_EXISTS));

        return CacheConstants.LOGIN_TOKEN_PREFIX + userId;
    }

    public void refreshLoginUser(String username, String userId) {
        String tokenKey = CacheConstants.LOGIN_TOKEN_PREFIX + userId;
        LoginUser loginUser = redisService.getCacheObject(tokenKey, LoginUser.class);
        loginUser.setUsername(username);
        redisService.setCacheObject(tokenKey, loginUser);
    }
}
