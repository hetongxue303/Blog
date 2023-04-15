package com.blog.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hy
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties("jwt.config")
public class JWTProperties {

    private String secret;
    private String header = "Authorization";
    private Long expiration = null;
    private String headPrefix = "Bearer ";
    private Boolean isHead = true;

}