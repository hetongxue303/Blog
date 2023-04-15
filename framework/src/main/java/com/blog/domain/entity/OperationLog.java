package com.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 日志操作模型
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("blog_operation_log")
@Schema(name = "日志操作模型")
public class OperationLog {

    @TableId
    @Schema(title = "ID")
    private Integer id;

    @Schema(title = "操作模块")
    private String optModule;

    @Schema(title = "操作URI")
    private String optUri;

    @Schema(title = "操作类型")
    private String optType;

    @Schema(title = "操作方法")
    private String optMethod;

    @Schema(title = "操作描述")
    private String optDesc;

    @Schema(title = "请求方式")
    private String requestMethod;

    @Schema(title = "请求参数")
    private String requestParam;

    @Schema(title = "响应数据")
    private String responseData;

    @Schema(title = "用户ID")
    private Long userId;

    @Schema(title = "用户昵称")
    private String nickname;

    @Schema(title = "IP地址")
    private String ipAddress;

    @Schema(title = "操作地址")
    private String ipSource;

    @TableLogic
    @Schema(title = "是否删除", description = "0：未删除(默认) 1：已删除")
    private Boolean isDel;

    @Schema(title = "创建人")
    private Long createBy;

    @Schema(title = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(title = "更新人")
    private Long updateBy;

    @Schema(title = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}