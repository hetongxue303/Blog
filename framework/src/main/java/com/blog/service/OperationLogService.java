package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.OperationLog;
import com.blog.domain.vo.SearchVo;

/**
 * 操作日志业务
 *
 * @author hy
 * @version 1.0
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 通过监听添加日志
     */
    void saveOperationLog(OperationLog operationLog);

    Result selectPage(SearchVo searchVo);

}