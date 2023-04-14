package com.blog.controller;

import com.blog.annotation.Logging;
import com.blog.domain.dto.Result;
import com.blog.domain.vo.SearchVo;
import com.blog.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 标签模块
 *
 * @author hy
 * @version 1.0
 */
@RestController
@RequestMapping("tags")
@Tag(name = "标签模块", description = "标签模块的描述")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("all")
    @Logging("查询所有标签")
    @Operation(summary = "获取所有标签", description = "获取所有标签")
    public Result getTagsAll() {
        return tagService.selectList();
    }

    @GetMapping("page")
    @Logging("分页获取标签")
    @Operation(summary = "分页获取标签", description = "分页获取标签")
    public Result getTagsAdmin(SearchVo searchVo) {
        return tagService.searchList(searchVo);
    }

}