package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.Category;
import com.blog.domain.vo.CategoryVo;
import com.blog.domain.vo.SearchVo;
import com.blog.handler.exception.customs.SystemException;
import com.blog.mapper.CategoryMapper;
import com.blog.service.CategoryService;
import com.blog.utils.BeanCopyUtil;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 分类业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Result selectAll() {
        return Result.success(categoryMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        //        wrapper.eq(Category::getStatus, SystemConstant.STATUS_ENABLED)
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), Category::getName, searchVo.getKeywords())
                .orderByDesc(Category::getId);
        Page<Category> data = categoryMapper.selectPage(MBPUtil.generatePage(searchVo, Category.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveCategory(CategoryVo CategoryVo) {
        // 1.判断是否存在相同分类名
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(CategoryVo.getName()), Category::getName, CategoryVo.getName());
        Category category = categoryMapper.selectOne(wrapper);
        if (Objects.nonNull(category))
            throw new SystemException("分类名已存在");
        // 2.保存分类 并返回结果
        return Result.isStatus(categoryMapper.insert(BeanCopyUtil.copyBean(CategoryVo, Category.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateCategory(CategoryVo CategoryVo) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(CategoryVo.getName()), Category::getName, CategoryVo.getName());
        Category category = categoryMapper.selectOne(wrapper);
        if (Objects.nonNull(category) && !Objects.deepEquals(CategoryVo.getId(), category.getId()))
            throw new SystemException("分类名已存在");
        // 2.保存标签 并返回结果
        return Result.isStatus(categoryMapper.updateById(BeanCopyUtil.copyBean(CategoryVo, Category.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteCategory(Long id) {
        // TODO 1.判断该标签下是否存在文章
        // select count(*) form 文章表 in (id); 如果大于0则表示：删除失败，该标签下存在文章
        // 2.删除标签 并返回结果
        return Result.isStatus(categoryMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteCategory(List<Long> ids) {
        // TODO 1.判断该标签下是否存在文章
        // select count(*) form 文章表 in (ids); 如果大于0则表示：删除失败，该标签下存在文章
        // 2.删除标签 并返回结果
        return Result.isStatus(categoryMapper.deleteBatchIds(ids));
    }

    @Override
    public Result searchCategory(String keywords) {
        return Result.success(categoryMapper.selectList(new LambdaQueryWrapper<Category>().like(Objects.nonNull(keywords), Category::getName, keywords)));
    }
}