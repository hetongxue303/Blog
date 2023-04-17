package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.Log;
import com.blog.domain.vo.SearchVo;
import com.blog.mapper.LogMapper;
import com.blog.service.LogService;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 日志业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Resource
    private LogMapper operationLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOperationLog(Log operationLog) {
        operationLogMapper.insert(operationLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteOperationLogs(Long id) {
        return Result.isStatus(operationLogMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteOperationLogs(List<Long> ids) {
        return Result.isStatus(operationLogMapper.deleteBatchIds(ids));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), Log::getOptModule, searchVo.getKeywords()).or()
                .like(Objects.nonNull(searchVo.getKeywords()), Log::getOptDesc, searchVo.getKeywords())
                .orderByDesc(Log::getId);
        Page<Log> data = operationLogMapper.selectPage(MBPUtil.generatePage(searchVo, Log.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }
}