package com.fancier.foj.common.core.utils;

import com.fancier.foj.common.core.constant.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Slf4j
public class JwtUtils {
    /**
     * ⽣成令牌
     *
     * @param claims 数据
     * @param secret 密钥
     * @return 令牌
     */
    public static String createToken(Map<String, Object> claims, String secret ) {

        return Jwts. builder ().setClaims(claims)
                .signWith(SignatureAlgorithm. HS512, secret)
                .compact();
    }
    /**
     * 从令牌中获取数据
     *
     * @param token 令牌
     * @param secret 密钥
     * @return 数据
     */
    public static Claims parseToken(String token, String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static String getUserId(Claims claims) {
        return toStr(claims.get(JwtConstants.LOGIN_USER_ID));
    }

    public static String getUserId(String token, String secret) {
        Claims claims;
        try {
            claims = parseToken(token, secret);
            if (Objects.isNull(claims)) {
                log.error("解析 token: {} 出现异常", token);
            }
            return toStr(claims.get(JwtConstants.LOGIN_USER_ID));
        } catch (Exception e) {
            log.error("解析 token: {} 出现异常", token, e);
            return "";
        }
    }

    private static String toStr(Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
}
