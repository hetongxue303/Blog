package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨越配置
 *
 * @author hy
 * @version 1.0
 */
@Configuration
public class CorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        // 允许跨域的源
        configuration.addAllowedOrigin("*");
        // 允许跨域的头
        configuration.addAllowedHeader("*");
        // 允许跨域的请求方法
        configuration.addAllowedMethod("*");
        // 是否跨域发送cookie
        configuration.setAllowCredentials(true);
        // 设置最长期限
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

}