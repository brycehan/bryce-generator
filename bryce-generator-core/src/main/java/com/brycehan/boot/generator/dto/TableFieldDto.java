package com.brycehan.boot.generator.dto;

import com.brycehan.boot.generator.common.dto.BasePageDto;
import com.brycehan.boot.generator.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 代码生成表字段数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TableFieldDto extends BasePageDto {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 表ID
     */
    private Long tableId;

    /**
     * 字段名称
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String fieldName;

    /**
     * 字段类型
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String fieldType;

    /**
     * 字段说明
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String fieldComment;

    /**
     * 属性名称
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String attrName;

    /**
     * 属性类型
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String attrType;

    /**
     * 属性包名
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String packageName;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 自动填充（DEFAULT, INSERT, UPDATE, INSERT_UPDATE）
     */
    @Size(max = 20, groups = QueryGroup.class)
    private String autoFill;

    /**
     * 主键（0：否，1：是）
     */
    private Boolean primaryKey;

    /**
     * 基类字段（0：否，1：是）
     */
    private Boolean baseField;

    /**
     * 表单项（0：否，1：是）
     */
    private Boolean formItem;

    /**
     * 表单项类型
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String formItemType;

    /**
     * 表单字典类型
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String formDict;

    /**
     * 表单必填（0：否，1：是）
     */
    private Boolean formRequired;

    /**
     * 表单校验器
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String formValidator;

    /**
     * 列表项（0：否，1：是）
     */
    private Boolean gridItem;

    /**
     * 列表排序
     */
    private Boolean gridSort;

    /**
     * 查询项（0：否，1：是）
     */
    private Boolean queryItem;

    /**
     * 查询方式
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String queryType;

    /**
     * 查询表单类型
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String queryFormType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
