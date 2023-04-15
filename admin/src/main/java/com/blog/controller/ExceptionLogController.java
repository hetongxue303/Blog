package com.blog.controller;

import com.blog.annotation.OperationLogging;
import com.blog.constants.OptTypeConstant;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.SearchVo;
import com.blog.service.ExceptionLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("exception")
@Tag(name = "异常日志模块")
public class ExceptionLogController {

    @Resource
    private ExceptionLogService exceptionLogService;

    @Operation(summary = "获取异常日志")
    @GetMapping("logs")
    public Result listExceptionLogs(@Valid @RequestBody SearchVo searchVo) {
        return Result.success(exceptionLogService.selectList(searchVo));
    }

    @OperationLogging(OptTypeConstant.DELETE)
    @Operation(summary = "批量删除异常日志")
    @DeleteMapping("batchDelete")
    public Result batchDeleteException(@RequestBody List<Long> ids) {
        return exceptionLogService.batchDeleteException(ids);
    }

    @OperationLogging(OptTypeConstant.DELETE)
    @Operation(summary = "删除异常日志")
    @DeleteMapping("delete/{id}")
    public Result batchDeleteException(@PathVariable("id") Long id) {
        return exceptionLogService.deleteException(id);
    }

}