package com.blog.controller;

import com.blog.annotation.OperationLogging;
import com.blog.constants.OptTypeConstant;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.SearchVo;
import com.blog.service.OperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("log")
@Tag(name = "操作日志模块")
public class OperationLogController {

    @Resource
    private OperationLogService operationLogService;

    @Operation(summary = "查看操作日志")
    @GetMapping("list")
    public Result listOperationLogs(@Valid @RequestBody SearchVo searchVo) {
        return operationLogService.selectList(searchVo);
    }

    @OperationLogging(OptTypeConstant.DELETE)
    @Operation(summary = "删除操作日志")
    @DeleteMapping("delete/{id}")
    public Result deleteOperationLogs(@PathVariable("id") Long id) {
        return operationLogService.deleteOperationLogs(id);
    }

    @OperationLogging(OptTypeConstant.DELETE)
    @Operation(summary = "批量删除操作日志")
    @DeleteMapping("batchDelete")
    public Result deleteOperationLogs(@RequestBody List<Long> ids) {
        return operationLogService.batchDeleteOperationLogs(ids);
    }

}