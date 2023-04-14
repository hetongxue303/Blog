package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.constants.SystemConstants;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.Tag;
import com.blog.domain.vo.SearchVo;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 标签业务实现
 *
 * @author hy
 * @version 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public Result selectList() {
        return Result.success(this.list());
    }

    @Override
    public Result searchList(SearchVo searchVo) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getStatus, SystemConstants.STATUS_ENABLED);
        wrapper.like(Objects.nonNull(searchVo.getTagName()), Tag::getName, searchVo.getTagName());
        Page<Tag> data = tagMapper.selectPage(MBPUtil.generatePage(searchVo, Tag.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }
}