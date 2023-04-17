package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.Log;
import com.blog.domain.vo.SearchVo;

import java.util.List;

/**
 * 操作日志业务
 *
 * @author hy
 * @version 1.0
 */
public interface LogService extends IService<Log> {

    /**
     * 通过监听添加日志
     */
    void saveOperationLog(Log operationLog);

    Result selectList(SearchVo searchVo);

    Result deleteOperationLogs(Long id);

    Result batchDeleteOperationLogs(List<Long> ids);
}