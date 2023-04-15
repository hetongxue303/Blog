package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.Category;
import com.blog.domain.vo.CategoryVo;
import com.blog.domain.vo.SearchVo;

import java.util.List;

/**
 * 分类业务
 *
 * @author hy
 * @version 1.0
 */
public interface CategoryService extends IService<Category> {


    /**
     * 查询所有分类
     */
    Result selectAll();

    /**
     * 分页查询分类
     *
     * @param searchVo 查询条件
     */
    Result selectList(SearchVo searchVo);

    /**
     * 保存分类
     *
     * @param CategoryVo 分类VO
     */
    Result saveCategory(CategoryVo CategoryVo);

    /**
     * 更新分类
     *
     * @param CategoryVo 分类VO
     */
    Result updateCategory(CategoryVo CategoryVo);

    /**
     * 删除分类
     *
     * @param id 分类ID
     */
    Result deleteCategory(Long id);

    /**
     * 批量删除分类
     *
     * @param ids 分类ID列表
     */
    Result batchDeleteCategory(List<Long> ids);

}