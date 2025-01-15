package com.fancier.foj.common.security.config;

import com.fancier.foj.common.security.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author <a href="https://github.com/hola1009">fancier</a>
 **/
@Configuration
@RequiredArgsConstructor
public class WebMvcConfigure implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/**/login")
                .addPathPatterns("/**");
    }
}
