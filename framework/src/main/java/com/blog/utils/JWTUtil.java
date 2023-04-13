package com.blog.utils;

import com.blog.handler.exception.customs.SystemException;
import io.jsonwebtoken.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * jwt工具类
 *
 * @author 何同学
 * @version 1.0
 */
@Data
@Slf4j
@ConfigurationProperties("jwt.config")
@Component
public class JWTUtil {
    private String secret;
    private String header = "Authorization";
    private Long expiration = null;
    private String headPrefix = "Bearer ";
    private Boolean isHead = true;

    public String conversionToken(String token) {
        if (isHead) {
            if (!StringUtils.startsWithIgnoreCase(token, headPrefix))
                throw new SystemException("JWT类型错误");
            return token.substring(headPrefix.length());
        }
        return token;
    }

    public Object getClaims(String token, String key) {
        return getClaims(conversionToken(token)).get(key);
    }

    public Boolean isExpiration(String token) {
        return getClaims(conversionToken(token)).getExpiration().before(new Date());
    }

    public String createToken(@NotNull Map<String, Object> claims) {
        long currentTimeMillis = getCurrentTimeMillis();
        return Jwts.builder().setHeaderParam("typ", "JWT").setId(getUUID()).setClaims(claims)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(Objects.isNull(expiration) ? null : new Date(currentTimeMillis + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, generalKey()).compact();
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(conversionToken(token)).getBody();
        } catch (ExpiredJwtException e) {
            log.error("JWT过期");
            throw new SystemException("JWT过期", e);
        } catch (UnsupportedJwtException e) {
            log.error("不支持JWT");
            throw new SystemException("不支持JWT", e);
        } catch (MalformedJwtException e) {
            log.error("JWT格式异常");
            throw new SystemException("JWT格式异常", e);
        } catch (SignatureException e) {
            log.error("JWT签名一场");
            throw new SystemException("JWT签名一场", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT非法参数");
            throw new SystemException("JWT非法参数", e);
        }
    }

    private long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}