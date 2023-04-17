package com.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.domain.entity.Article;
import com.blog.mapper.ArticleMapper;
import com.blog.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章业务处理
 *
 * @author hy
 * @version 1.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}