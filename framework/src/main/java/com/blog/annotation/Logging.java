package com.blog.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author hy
 * @version 1.0
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {
    String value() default "";
}