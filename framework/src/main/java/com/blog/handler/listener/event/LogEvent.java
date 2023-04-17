package com.blog.handler.listener.event;

import com.blog.domain.entity.Log;
import org.springframework.context.ApplicationEvent;

/**
 * 日志操作事件
 *
 * @author hy
 * @version 1.0
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Log operationLog) {
        super(operationLog);
    }

}