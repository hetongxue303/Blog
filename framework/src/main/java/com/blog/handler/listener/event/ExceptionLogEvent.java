package com.blog.handler.listener.event;

import com.blog.domain.entity.ExceptionLog;
import org.springframework.context.ApplicationEvent;

/**
 * 异常日志事件
 *
 * @author hy
 * @version 1.0
 */
public class ExceptionLogEvent extends ApplicationEvent {
    public ExceptionLogEvent(ExceptionLog exceptionLog) {
        super(exceptionLog);
    }
}
