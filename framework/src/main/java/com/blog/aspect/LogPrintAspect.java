package com.blog.aspect;

import com.alibaba.fastjson2.JSON;
import com.blog.annotation.LogPrint;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 日志打印注解切面
 *
 * @author hy
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public class LogPrintAspect {

    @Pointcut("@annotation(com.blog.annotation.LogPrint)")
    public void logPrint() {
    }

    @Around("logPrint()")
    public Object handlerLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        LogPrint logging = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LogPrint.class);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info("===============================begin===============================");
        log.info("value          : {}", logging.value());
        log.info("http method    : {}", request.getMethod());
        log.info("ip             : {}", request.getRemoteHost());
        log.info("url            : {}", request.getRequestURL());
        log.info("class method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature()
                .getName());
        log.info("request args   : {}", JSON.toJSONString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.info("response       : {}", JSON.toJSONString(result));
        log.info("take           : {} ms", System.currentTimeMillis() - beginTime);
        log.info("================================end================================");
        return result;
    }

}