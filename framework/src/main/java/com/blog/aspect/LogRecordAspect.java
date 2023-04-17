package com.blog.aspect;

import com.alibaba.fastjson2.JSON;
import com.blog.annotation.LogRecord;
import com.blog.domain.entity.Log;
import com.blog.handler.listener.event.LogEvent;
import com.blog.utils.IPUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 日志操作切面类
 *
 * @author hy
 * @version 1.0
 */
@Aspect
@Component
@SuppressWarnings("all")
public class LogRecordAspect {

    @Resource
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.blog.annotation.LogRecord)")
    public void LogRecordPointCut() {
    }

    @AfterReturning(value = "LogRecordPointCut()", returning = "keys")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) Objects.requireNonNull(requestAttributes)
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Log operationLog = new Log();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Tag tag = (Tag) signature.getDeclaringType().getAnnotation(Tag.class);
        Operation operation = method.getAnnotation(Operation.class);
        LogRecord operationLogging = method.getAnnotation(LogRecord.class);
        operationLog.setOptModule(tag.name());
        operationLog.setOptType(operation.summary());
        operationLog.setOptDesc(operationLogging.value());
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        methodName = className + "." + methodName;
        operationLog.setRequestMethod(Objects.requireNonNull(request).getMethod());
        operationLog.setOptMethod(methodName);
        if (joinPoint.getArgs().length > 0) {
            if (joinPoint.getArgs()[0] instanceof MultipartFile) {
                operationLog.setRequestParam("file");
            } else {
                operationLog.setRequestParam(JSON.toJSONString(joinPoint.getArgs()));
            }
        }
        operationLog.setResponseData(JSON.toJSONString(keys));
        operationLog.setUserId(1L);
        operationLog.setNickname("test");
        String ipAddress = IPUtil.getIpAddress(request);
        operationLog.setIpAddress(ipAddress);
        operationLog.setIpSource(IPUtil.getIpSource(ipAddress));
        operationLog.setOptUri(request.getRequestURI());
        applicationContext.publishEvent(new LogEvent(operationLog));
    }

}