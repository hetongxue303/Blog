package com.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章标签实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article")
@Schema(name = "文章标签实体")
public class ArticleTag implements Serializable {

    @TableId
    @Schema(title = "文章标签ID")
    private Long id;

    @Schema(title = "文章ID")
    private Long articleId;

    @Schema(title = "标签ID")
    private Long tagId;

    @TableLogic
    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "创建人")
    private Long createBy;

    @Schema(title = "发表时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(title = "更新人")
    private Long updateBy;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
