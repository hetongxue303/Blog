package com.blog.utils;

import com.blog.config.properties.JWTProperties;
import com.blog.handler.exception.customs.SystemException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * jwt工具类
 *
 * @author hy
 * @version 1.0
 */
@Slf4j
@Component
public class JWTUtil {

    @Resource
    private JWTProperties jwtProperties;

    public String conversionToken(String token) {
        if (jwtProperties.getIsHead()) {
            if (!StringUtils.startsWithIgnoreCase(token, jwtProperties.getHeadPrefix()))
                throw new SystemException("JWT类型错误");
            return token.substring(jwtProperties.getHeadPrefix().length());
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
                .setExpiration(Objects.isNull(jwtProperties.getExpiration()) ? null : new Date(currentTimeMillis + jwtProperties.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, generalKey()).compact();
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(conversionToken(token))
                    .getBody();
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
        byte[] encodedKey = Base64.getDecoder().decode(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}