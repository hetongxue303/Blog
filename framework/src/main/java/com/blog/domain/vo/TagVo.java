package com.blog.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 标签VO
 *
 * @author hy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "标签VO")
public class TagVo implements Serializable {

    @Schema(title = "ID")
    private Long id;
    @Schema(title = "名称")
    @NotBlank(message = "标签名不能为空")
    private String name;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "状态", description = "0：禁用 1：启用(默认)")
    private Boolean status;
    @Schema(title = "创建人")
    private Long createBy;
    @Schema(title = "创建时间")
    private Date createTime;
    @Schema(title = "更新人")
    private Long updateBy;
    @Schema(title = "更新时间")
    private Date updateTime;

}