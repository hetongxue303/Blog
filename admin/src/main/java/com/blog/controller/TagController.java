package com.blog.controller;

import com.blog.annotation.LogPrint;
import com.blog.annotation.LogRecord;
import com.blog.constants.LogTypeConstant;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.SearchVo;
import com.blog.domain.vo.TagVo;
import com.blog.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 标签模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("tags")
@Tag(name = "标签模块")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("all")
    @LogPrint("查询所有标签")
    @Operation(summary = "获取所有标签")
    public Result getTagAll() {
        return tagService.selectAll();
    }

    @GetMapping("search")
    @LogPrint("搜索标签")
    @Operation(summary = "搜索标签")
    public Result searchCategory(String keywords) {
        return tagService.searchTag(keywords);
    }

    @GetMapping("list")
    @LogPrint("分页/搜索标签")
    @Operation(summary = "分页/搜索标签")
    public Result getTagList(SearchVo searchVo) {
        return tagService.selectList(searchVo);
    }

    @PostMapping("add")
    @LogPrint("添加标签")
    @Operation(summary = "添加标签")
    @LogRecord(LogTypeConstant.INSERT)
    public Result addTag(@Valid @RequestBody TagVo tagVo) {
        return tagService.saveTag(tagVo);
    }

    @PutMapping("update")
    @LogPrint("更新标签")
    @Operation(summary = "更新标签")
    @LogRecord(LogTypeConstant.UPDATE)
    public Result updateTag(@Valid @RequestBody TagVo tagVo) {
        return tagService.updateTag(tagVo);
    }

    @DeleteMapping("delete/{id}")
    @LogPrint("删除标签")
    @Operation(summary = "删除标签", description = "根据ID删除标签")
    @LogRecord(LogTypeConstant.DELETE)
    public Result removeTag(@PathVariable("id") Long id) {
        return tagService.deleteTag(id);
    }

    @DeleteMapping("batchDelete")
    @LogPrint("批量删除标签")
    @Operation(summary = "批量删除标签", description = "根据ID列表批量删除标签")
    @LogRecord(LogTypeConstant.BATCH_DELETE)
    public Result batchRemoveTag(@RequestBody List<Long> ids) {
        return tagService.batchDeleteTag(ids);
    }


}