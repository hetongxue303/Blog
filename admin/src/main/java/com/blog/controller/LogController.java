package com.blog.controller;

import com.blog.annotation.LogRecord;
import com.blog.constants.LogTypeConstant;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.SearchVo;
import com.blog.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 日志模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("log")
@Tag(name = "日志模块")
public class LogController {

    @Resource
    private LogService logService;

    @GetMapping("list")
    @Operation(summary = "查看操作日志")

    public Result listOperationLogs(@Valid @RequestBody SearchVo searchVo) {
        return logService.selectList(searchVo);
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "删除操作日志")
    @LogRecord(LogTypeConstant.DELETE)
    public Result deleteOperationLogs(@PathVariable("id") Long id) {
        return logService.deleteOperationLogs(id);
    }

    @DeleteMapping("batchDelete")
    @Operation(summary = "批量删除操作日志")
    @LogRecord(LogTypeConstant.BATCH_DELETE)
    public Result deleteOperationLogs(@RequestBody List<Long> ids) {
        return logService.batchDeleteOperationLogs(ids);
    }

}