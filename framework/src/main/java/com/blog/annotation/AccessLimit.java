package com.blog.annotation;

import java.lang.annotation.*;

/**
 * 访问限制注解
 *
 * @author hy
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    int seconds();

    int maxCount() default 1;
}
