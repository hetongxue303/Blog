package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.ExceptionLog;
import com.blog.domain.vo.SearchVo;

import java.util.List;

/**
 * 异常日志业务
 *
 * @author hy
 * @version 1.0
 */
public interface ExceptionLogService extends IService<ExceptionLog> {

    void saveExceptionLog(ExceptionLog exceptionLog);

    Result selectList(SearchVo searchVo);

    Result deleteException(Long id);

    Result batchDeleteException(List<Long> ids);
}