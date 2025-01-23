package com.fancier.foj.common.security.interceptor;

import cn.hutool.core.util.StrUtil;
import com.fancier.foj.common.core.constant.HttpConstants;
import com.fancier.foj.common.core.utils.JwtUtils;
import com.fancier.foj.common.core.utils.UserHolder;
import com.fancier.foj.common.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Value("${jwt.secret}")
    private String secret; // 盐值

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String token = getToken(request);
        // 尝试延长有效期
        tokenService.extendToken(token);

        String userId = JwtUtils.getUserId(token, secret);
        UserHolder.set(Long.parseLong(userId));
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
        UserHolder.remove();
    }

    /**
     * 从请求头中获取 token
     */
    private String getToken(@NonNull HttpServletRequest request) {
        String token = request.getHeader(HttpConstants.AUTHENTICATION);

        if (StrUtil.isNotEmpty(token) && token.startsWith(HttpConstants.PREFIX)) {
            token = token.replaceFirst(HttpConstants.PREFIX, "");
        }

        return token;
    }

}
