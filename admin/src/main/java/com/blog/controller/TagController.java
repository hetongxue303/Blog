package com.blog.controller;

import com.blog.annotation.Logging;
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
    @Logging("查询所有标签")
    @Operation(summary = "获取所有标签")
    public Result getTagAll() {
        return tagService.selectAll();
    }

    @GetMapping("list")
    @Logging("分页/搜索标签")
    @Operation(summary = "分页/搜索标签")
    public Result getTagPage(SearchVo searchVo) {
        return tagService.selectList(searchVo);
    }

    @PostMapping("add")
    @Logging("添加标签")
    @Operation(summary = "添加标签")
    public Result addTag(@Valid @RequestBody TagVo tagVo) {
        return tagService.saveTag(tagVo);
    }

    @PutMapping("update")
    @Logging("更新标签")
    @Operation(summary = "更新标签")
    public Result updateTag(@Valid @RequestBody TagVo tagVo) {
        return tagService.updateTag(tagVo);
    }

    @DeleteMapping("delete/{id}")
    @Logging("删除标签")
    @Operation(summary = "删除标签", description = "根据ID删除标签")
    public Result removeTag(@PathVariable("id") Long id) {
        return tagService.deleteTag(id);
    }

    @DeleteMapping("batchDelete")
    @Logging("批量删除标签")
    @Operation(summary = "批量删除标签", description = "根据ID列表批量删除标签")
    public Result batchRemoveTag(@RequestBody List<Long> ids) {
        return tagService.batchDeleteTag(ids);
    }


}