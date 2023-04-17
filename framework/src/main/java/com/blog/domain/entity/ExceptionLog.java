package com.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 异常日志实体
 *
 * @author hy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("blog_exception_log")
@Schema(name = "异常日志实体")
public class ExceptionLog implements Serializable {

    @TableId
    @Schema(title = "异常日志ID")
    private Integer id;

    @Schema(title = "请求接口")
    private String optUri;

    @Schema(title = "请求方式")
    private String optMethod;

    @Schema(title = "请求方式")
    private String requestMethod;

    @Schema(title = "请求参数")
    private String requestParam;

    @Schema(title = "操作描述")
    private String optDesc;

    @Schema(title = "异常信息")
    private String exceptionInfo;

    @Schema(title = "IP地址")
    private String ipAddress;

    @Schema(title = "IP来源")
    private String ipSource;

    @Schema(title = "操作时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime operationTime;

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
