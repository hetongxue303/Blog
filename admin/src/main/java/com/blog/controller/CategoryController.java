package com.blog.controller;

import com.blog.annotation.LogPrint;
import com.blog.annotation.LogRecord;
import com.blog.constants.LogTypeConstant;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.CategoryVo;
import com.blog.domain.vo.SearchVo;
import com.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 分类模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("category")
@Tag(name = "分类模块")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("all")
    @LogPrint("查询所有分类")
    @Operation(summary = "获取所有分类")
    public Result getCategoryAll() {
        return categoryService.selectAll();
    }

    @GetMapping("search")
    @LogPrint("搜索分类")
    @Operation(summary = "搜索分类")
    public Result searchCategory(String keywords) {
        return categoryService.searchCategory(keywords);
    }

    @GetMapping("list")
    @LogPrint("分页/搜索分类")
    @Operation(summary = "分页/搜索分类")
    public Result getCategoryPage(SearchVo searchVo) {
        return categoryService.selectList(searchVo);
    }

    @PostMapping("add")
    @LogPrint("添加分类")
    @Operation(summary = "添加分类")
    @LogRecord(LogTypeConstant.INSERT)
    public Result addCategory(@Valid @RequestBody CategoryVo CategoryVo) {
        return categoryService.saveCategory(CategoryVo);
    }

    @PutMapping("update")
    @LogPrint("更新分类")
    @Operation(summary = "更新分类")
    @LogRecord(LogTypeConstant.UPDATE)
    public Result updateCategory(@Valid @RequestBody CategoryVo CategoryVo) {
        return categoryService.updateCategory(CategoryVo);
    }

    @DeleteMapping("delete/{id}")
    @LogPrint("删除分类")
    @Operation(summary = "删除分类", description = "根据ID删除分类")
    @LogRecord(LogTypeConstant.DELETE)
    public Result removeCategory(@PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @DeleteMapping("batchDelete")
    @LogPrint("批量删除分类")
    @Operation(summary = "批量删除分类", description = "根据ID列表批量删除分类")
    @LogRecord(LogTypeConstant.BATCH_DELETE)
    public Result batchRemoveCategory(@RequestBody List<Long> ids) {
        return categoryService.batchDeleteCategory(ids);
    }


}