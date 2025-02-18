package com.brycehan.boot.generator.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.brycehan.boot.generator.common.validator.SaveGroup;
import com.brycehan.boot.generator.common.validator.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 代码生成表字段
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@TableName("brc_gen_table_field")
public class TableField implements Serializable {

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
     * 表ID
     */
    private Long tableId;

    /**
     * 字段名
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String fieldName;

    /**
     * 字段类型
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String fieldType;

    /**
     * 字段说明
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String fieldComment;

    /**
     * 属性名称
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String attrName;

    /**
     * 属性类型
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String attrType;

    /**
     * 属性包名
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String packageName;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 自动填充（DEFAULT, INSERT, UPDATE, INSERT_UPDATE）
     */
    @Length(max = 20, groups = {SaveGroup.class, UpdateGroup.class})
    private String autoFill;

    /**
     * 主键（0：否，1：是）
     */
    private boolean primaryKey;

    /**
     * 字符最大长度
     */
    private Long characterMaximumLength;

    /**
     * 基类字段（0：否，1：是）
     */
    private boolean baseField;

    /**
     * 表单项（0：否，1：是）
     */
    private boolean formItem;

    /**
     * 表单项类型
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String formItemType;

    /**
     * 表单字典类型
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String formDict;

    /**
     * 表单必填（0：否，1：是）
     */
    private boolean formRequired;

    /**
     * 表单校验器
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String formValidator;

    /**
     * 列表项（0：否，1：是）
     */
    private boolean gridItem;

    /**
     * 列表排序（0：否，1：是）
     */
    private boolean gridSort;

    /**
     * 查询项（0：否，1：是）
     */
    private boolean queryItem;

    /**
     * 查询方式
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String queryType;

    /**
     * 查询表单类型
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String queryFormType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Null(groups = {SaveGroup.class, UpdateGroup.class})
    @com.baomidou.mybatisplus.annotation.TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
