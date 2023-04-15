package com.blog.handler.listener;

import com.blog.domain.entity.ExceptionLog;
import com.blog.domain.entity.OperationLog;
import com.blog.handler.event.ExceptionLogEvent;
import com.blog.handler.event.OperationLogEvent;
import com.blog.service.ExceptionLogService;
import com.blog.service.OperationLogService;
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
    private OperationLogService operationLogService;
    @Resource
    private ExceptionLogService exceptionLogService;

    @Async
    @EventListener(OperationLogService.class)
    public void saveOperationLog(OperationLogEvent operationLogEvent) {
        operationLogService.saveOperationLog((OperationLog) operationLogEvent.getSource());
    }

    @Async
    @EventListener(ExceptionLogEvent.class)
    public void saveExceptionLog(ExceptionLogEvent exceptionLogEvent) {
        exceptionLogService.saveExceptionLog((ExceptionLog) exceptionLogEvent.getSource());
    }

}