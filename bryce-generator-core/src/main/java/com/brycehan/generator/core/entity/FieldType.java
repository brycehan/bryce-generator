package com.brycehan.generator.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
import com.brycehan.generator.core.validator.group.SaveGroup;
import com.brycehan.generator.core.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字段类型管理
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@TableName("brc_gen_field_type")
public class FieldType implements Serializable {

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
     * 列类型
     */
    @Size(max = 50, groups = {SaveGroup.class, UpdateGroup.class})
    private String columnType;

    /**
     * 属性类型
     */
    @Size(max = 50, groups = {SaveGroup.class, UpdateGroup.class})
    private String attrType;

    /**
     * 属性包名
     */
    @Size(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String packageName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Null(groups = {SaveGroup.class, UpdateGroup.class})
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @Size(max = 300, groups = {SaveGroup.class, UpdateGroup.class})
    private String remark;

}
