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
 * 文章实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("b_article")
@Schema(name = "文章实体")
public class Article implements Serializable {

    @TableId
    @Schema(title = "文章ID")
    private Long id;

    @Schema(title = "作者ID")
    private Long userId;

    @Schema(title = "标签ID")
    private Long tagId;

    @Schema(title = "分类ID")
    private Long categoryId;

    @Schema(title = "封面")
    private String cover;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "内容")
    private String content;

    @Schema(title = "是否置顶", description = "0否(默认) 1是")
    private Boolean isTop;

    @Schema(title = "是否推荐", description = "0否(否) 1是")
    private Boolean isFeatured;

    @Schema(title = "状态值", description = "1已发布 2草稿(默认)")
    private Integer status;

    @Schema(title = "类型", description = "1原创(默认) 2转载 3翻译")
    private Integer type;

    @Schema(title = "权限", description = "1公开(默认) 2私有")
    private Integer authority;

    @Schema(title = "原文链接")
    private String originalUrl;

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
