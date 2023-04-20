package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.domain.dto.Result;
import com.blog.domain.entity.Article;
import com.blog.domain.vo.ArticleVo;
import com.blog.domain.vo.SearchVo;

import java.util.List;

/**
 * 文章业务
 *
 * @author hy
 * @version 1.0
 */
public interface ArticleService extends IService<Article> {

    Result deleteArticle(Long id);

    Result batchDeleteArticle(List<Long> ids);

    Result updateArticle(ArticleVo articleVo);

    Result saveArticle(ArticleVo articleVo);

    Result selectList(SearchVo searchVo);

    Result selectAll();
}