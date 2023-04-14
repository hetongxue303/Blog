package com.blog.controller;

import com.blog.annotation.Logging;
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
@Tag(name = "分类模块", description = "分类模块")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("all")
    @Logging("查询所有分类")
    @Operation(summary = "获取所有分类")
    public Result getCategoryAll() {
        return categoryService.selectList();
    }

    @GetMapping("page")
    @Logging("分页/搜索分类")
    @Operation(summary = "分页/搜索分类")
    public Result getCategoryPage(SearchVo searchVo) {
        return categoryService.selectPage(searchVo);
    }

    @PostMapping("add")
    @Logging("添加分类")
    @Operation(summary = "添加分类")
    public Result addCategory(@Valid @RequestBody CategoryVo CategoryVo) {
        return categoryService.saveCategory(CategoryVo);
    }

    @PutMapping("update")
    @Logging("更新分类")
    @Operation(summary = "更新分类")
    public Result updateCategory(@Valid @RequestBody CategoryVo CategoryVo) {
        return categoryService.updateCategory(CategoryVo);
    }

    @DeleteMapping("delete/{id}")
    @Logging("删除分类")
    @Operation(summary = "删除分类", description = "根据ID删除分类")
    public Result removeCategory(@Valid @PathVariable("id") Long id) {
        return categoryService.deleteCategory(id);
    }

    @DeleteMapping("delete/batch")
    @Logging("批量删除分类")
    @Operation(summary = "批量删除分类", description = "根据ID列表批量删除分类")
    public Result batchRemoveCategory(@Valid @RequestBody List<Long> ids) {
        return categoryService.batchDeleteCategory(ids);
    }


}