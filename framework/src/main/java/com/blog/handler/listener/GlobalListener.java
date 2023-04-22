package com.blog.handler.listener;

import com.blog.domain.entity.ExceptionLog;
import com.blog.domain.entity.Log;
import com.blog.handler.listener.event.ExceptionLogEvent;
import com.blog.handler.listener.event.LogEvent;
import com.blog.service.ExceptionLogService;
import com.blog.service.LogService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 全局监听
 *
 * @author hy
 * @version 1.0
 */
@Component
public class GlobalListener {

    @Resource
    private LogService logService;
    @Resource
    private ExceptionLogService exceptionLogService;

    @Async
    @EventListener(LogService.class)
    public void saveOperationLog(LogEvent logEvent) {
        logService.saveOperationLog((Log) logEvent.getSource());
    }

    @Async
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent exceptionLogEvent) {
        exceptionLogService.saveExceptionLog((ExceptionLog) exceptionLogEvent.getSource());
    }

}