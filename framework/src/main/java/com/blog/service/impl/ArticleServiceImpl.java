package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.dto.Result;
import com.blog.domain.dto.ResultPage;
import com.blog.domain.entity.Article;
import com.blog.domain.entity.ArticleTag;
import com.blog.domain.vo.ArticleVo;
import com.blog.domain.vo.SearchVo;
import com.blog.mapper.ArticleMapper;
import com.blog.mapper.ArticleTagMapper;
import com.blog.service.ArticleService;
import com.blog.utils.BeanCopyUtil;
import com.blog.utils.MBPUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 文章业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagMapper articleTagMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteArticle(Long id) {
        return Result.isStatus(articleMapper.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteArticle(List<Long> ids) {
        return Result.isStatus(articleMapper.deleteBatchIds(ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateArticle(ArticleVo articleVo) {
        // TODO 更新前的操作
        return Result.isStatus(articleMapper.updateById(BeanCopyUtil.copyBean(articleVo, Article.class)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveArticle(ArticleVo articleVo) {
        Article article = BeanCopyUtil.copyBean(articleVo, Article.class);
        //        if (Objects.nonNull(articleMapper.selectOne(new LambdaQueryWrapper<Article>().eq(Article::getTitle, article.getTitle()))))
        //            throw new SystemException("文章标题已存在");
        // 新增文章
        int status = articleMapper.insert(article);
        // 新增关联表
        if (article.getStatus() == 1) {
            if (status > 0) {
                status = articleTagMapper.insert(new ArticleTag().setArticleId(article.getId())
                        .setTagId(article.getTagId()));
            }
        }
        return Result.isStatus(status);
    }

    @Override
    public Result selectList(SearchVo searchVo) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        //        wrapper.eq(Category::getStatus, SystemConstant.STATUS_ENABLED)
        wrapper.like(Objects.nonNull(searchVo.getKeywords()), Article::getTitle, searchVo.getKeywords())
                .orderByDesc(Article::getId);
        Page<Article> data = articleMapper.selectPage(MBPUtil.generatePage(searchVo, Article.class), wrapper);
        return Result.success(new ResultPage(data.getTotal(), data.getRecords()));
    }

    @Override
    public Result selectAll() {
        return Result.success(articleMapper.selectList(null));
    }

}