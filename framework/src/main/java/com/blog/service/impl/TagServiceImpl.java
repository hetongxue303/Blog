package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.Tags;
import com.blog.domain.vo.SearchVo;
import com.blog.domain.vo.TagVo;
import com.blog.handler.exception.customs.SystemException;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;
import com.blog.utils.BeanCopyUtil;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 标签业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tags> implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public Result selectAll() {
        return Result.success(tagMapper.selectList(null));
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Tags> wrapper = new LambdaQueryWrapper<>();
        //        wrapper.eq(Tags::getStatus, SystemConstant.STATUS_ENABLED)
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), Tags::getName, searchVo.getKeywords())
                .orderByDesc(Tags::getId);
        Page<Tags> data = tagMapper.selectPage(MBPUtil.generatePage(searchVo, Tags.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveTag(TagVo tagVo) {
        // 1.判断是否存在相同标签名
        LambdaQueryWrapper<Tags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(tagVo.getName()), Tags::getName, tagVo.getName());
        Tags tags = tagMapper.selectOne(wrapper);
        if (Objects.nonNull(tags))
            throw new SystemException("标签名已存在");
        // 2.保存标签 并返回结果
        return Result.isStatus(tagMapper.insert(BeanCopyUtil.copyBean(tagVo, Tags.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateTag(TagVo tagVo) {
        // 1.判断是否存在相同标签名
        LambdaQueryWrapper<Tags> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(tagVo.getName()), Tags::getName, tagVo.getName());
        Tags tags = tagMapper.selectOne(wrapper);
        if (Objects.nonNull(tags) && !Objects.deepEquals(tagVo.getId(), tags.getId()))
            throw new SystemException("标签名已存在");
        // 2.保存标签 并返回结果
        return Result.isStatus(tagMapper.updateById(BeanCopyUtil.copyBean(tagVo, Tags.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteTag(Long id) {
        // TODO 1.判断该标签下是否存在文章
        // select count(*) form 文章表 in (id); 如果大于0则表示：删除失败，该标签下存在文章
        // 2.删除标签 并返回结果
        return Result.isStatus(tagMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteTag(List<Long> ids) {
        // TODO 1.判断该标签下是否存在文章
        // select count(*) form 文章表 in (ids); 如果大于0则表示：删除失败，该标签下存在文章
        // 2.删除标签 并返回结果
        return Result.isStatus(tagMapper.deleteBatchIds(ids));
    }

    @Override
    public Result searchTag(String keywords) {
        return Result.success(tagMapper.selectList(new LambdaQueryWrapper<Tags>().like(Objects.nonNull(keywords), Tags::getName, keywords)));
    }
}