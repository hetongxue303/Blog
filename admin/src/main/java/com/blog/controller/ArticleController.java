package com.blog.controller;

import com.blog.annotation.LogPrint;
import com.blog.annotation.LogRecord;
import com.blog.constants.LogTypeConstant;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.ArticleVo;
import com.blog.domain.vo.SearchVo;
import com.blog.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 文章模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("article")
@Tag(name = "文章模块")
public class ArticleController {

    @Resource
    private ArticleService ArticleService;

    @GetMapping("all")
    @LogPrint("查询所有文章")
    @Operation(summary = "获取所有文章")
    public Result getArticleAll() {
        return ArticleService.selectAll();
    }

    @GetMapping("list")
    @LogPrint("分页/搜索文章")
    @Operation(summary = "分页/搜索文章")
    public Result getArticleList(SearchVo searchVo) {
        return ArticleService.selectList(searchVo);
    }

    @PostMapping("add")
    @LogPrint("添加文章")
    @Operation(summary = "添加文章")
    @LogRecord(LogTypeConstant.INSERT)
    public Result addArticle(@Valid @RequestBody ArticleVo ArticleVo) {
        return ArticleService.saveArticle(ArticleVo);
    }

    @PutMapping("update")
    @LogPrint("更新文章")
    @Operation(summary = "更新文章")
    @LogRecord(LogTypeConstant.UPDATE)
    public Result updateArticle(@Valid @RequestBody ArticleVo ArticleVo) {
        return ArticleService.updateArticle(ArticleVo);
    }

    @DeleteMapping("delete/{id}")
    @LogPrint("删除文章")
    @Operation(summary = "删除文章", description = "根据ID删除文章")
    @LogRecord(LogTypeConstant.DELETE)
    public Result removeArticle(@PathVariable("id") Long id) {
        return ArticleService.deleteArticle(id);
    }

    @DeleteMapping("batchDelete")
    @LogPrint("批量删除文章")
    @Operation(summary = "批量删除文章", description = "根据ID列表批量删除文章")
    @LogRecord(LogTypeConstant.BATCH_DELETE)
    public Result batchRemoveArticle(@RequestBody List<Long> ids) {
        return ArticleService.batchDeleteArticle(ids);
    }


}