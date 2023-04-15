package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.ExceptionLog;
import com.blog.domain.vo.SearchVo;
import com.blog.mapper.ExceptionLogMapper;
import com.blog.service.ExceptionLogService;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 异常日志业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogMapper, ExceptionLog> implements ExceptionLogService {

    @Resource
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveExceptionLog(ExceptionLog exceptionLog) {
        exceptionLogMapper.insert(exceptionLog);
    }

    @Override
    public Result selectPage(SearchVo searchVo) {
        LambdaQueryWrapper<ExceptionLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), ExceptionLog::getOptDesc, searchVo.getKeywords())
                .orderByDesc(ExceptionLog::getId);
        Page<ExceptionLog> data = exceptionLogMapper.selectPage(MBPUtil.generatePage(searchVo, ExceptionLog.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }
}