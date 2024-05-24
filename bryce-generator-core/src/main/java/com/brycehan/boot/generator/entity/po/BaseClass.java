package com.brycehan.boot.generator.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
import com.brycehan.boot.generator.common.validator.SaveGroup;
import com.brycehan.boot.generator.common.validator.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基类管理
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@TableName("brc_gen_base_class")
public class BaseClass implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Null(groups = SaveGroup.class)
    @NotNull(groups = UpdateGroup.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 基类编码
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String code;

    /**
     * 基类包名
     */
    @Size(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String packageName;

    /**
     * 基类字段，多个用英文逗号分隔
     */
    @Size(max = 500, groups = {SaveGroup.class, UpdateGroup.class})
    private String fields;

    /**
     * 备注
     */
    @Size(max = 300, groups = {SaveGroup.class, UpdateGroup.class})
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Null(groups = {SaveGroup.class, UpdateGroup.class})
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
