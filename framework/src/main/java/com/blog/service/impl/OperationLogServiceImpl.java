package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.OperationLog;
import com.blog.domain.vo.SearchVo;
import com.blog.mapper.OperationLogMapper;
import com.blog.service.OperationLogService;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 操作日志业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOperationLog(OperationLog operationLog) {
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
        LambdaQueryWrapper<OperationLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), OperationLog::getOptModule, searchVo.getKeywords()).or()
                .like(Objects.nonNull(searchVo.getKeywords()), OperationLog::getOptDesc, searchVo.getKeywords())
                .orderByDesc(OperationLog::getId);
        Page<OperationLog> data = operationLogMapper.selectPage(MBPUtil.generatePage(searchVo, OperationLog.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }
}