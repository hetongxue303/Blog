package com.blog.handler.event;

import com.blog.domain.entity.OperationLog;
import org.springframework.context.ApplicationEvent;

/**
 * 日志操作事件
 *
 * @author hy
 * @version 1.0
 */
public class OperationLogEvent extends ApplicationEvent {

    public OperationLogEvent(OperationLog operationLog) {
        super(operationLog);
    }

}