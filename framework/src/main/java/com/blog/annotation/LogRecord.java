package com.blog.annotation;

import com.blog.constants.LogTypeConstant;

import java.lang.annotation.*;

/**
 * 日志记录注解
 *
 * @author hy
 * @version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogRecord {
    String value() default LogTypeConstant.UNKNOWN;
}