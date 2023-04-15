package com.blog.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author hy
 * @version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogging {
    String value() default "";
}