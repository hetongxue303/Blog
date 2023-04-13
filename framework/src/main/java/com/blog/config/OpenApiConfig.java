package com.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApi配置
 *
 * @author hy
 * @version 1.0
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "个人博客", version = "0.1", description = "这是个人博客的API文档", termsOfService = "https://github.com/hetongxue303/blog", contact = @Contact(name = "何同学", email = "heyue.chongqing@aliyun.com", url = "https://blog.csdn.net/qq_14818715?spm=1000.2115.3001.5343"), license = @License(name = "Apache 2.0", url = "https://www.apache.org")))
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder().group("v 0.1").pathsToMatch("/**").build();
    }

}