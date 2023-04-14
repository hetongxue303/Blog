package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.Tag;
import com.blog.domain.vo.SearchVo;

/**
 * 标签业务
 *
 * @author hy
 * @version 1.0
 */
public interface TagService extends IService<Tag> {

    /**
     * 查询所有标签
     */
    Result selectList();

    /**
     * 查询标签
     */
    Result searchList(SearchVo searchVo);

}