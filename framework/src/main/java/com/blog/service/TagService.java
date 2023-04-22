package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.Tags;
import com.blog.domain.vo.SearchVo;
import com.blog.domain.vo.TagVo;

import java.util.List;

/**
 * 标签业务
 *
 * @author hy
 * @version 1.0
 */
public interface TagService extends IService<Tags> {

    /**
     * 查询所有标签
     */
    Result selectAll();

    /**
     * 分页查询标签
     *
     * @param searchVo 查询条件
     */
    Result selectList(SearchVo searchVo);

    /**
     * 保存标签
     *
     * @param tagVo 标签VO
     */
    Result saveTag(TagVo tagVo);

    /**
     * 更新标签
     *
     * @param tagVo 标签VO
     */
    Result updateTag(TagVo tagVo);

    /**
     * 删除标签
     *
     * @param id 标签ID
     */
    Result deleteTag(Long id);

    /**
     * 批量删除标签
     *
     * @param ids 标签ID列表
     */
    Result batchDeleteTag(List<Long> ids);

    Result searchTag(String keywords);
}