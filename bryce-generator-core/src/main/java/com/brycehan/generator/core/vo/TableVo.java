package com.brycehan.generator.core.vo;

import com.brycehan.generator.core.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 代码生成表视图对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TableVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 表名
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String tableName;

    /**
     * 类名
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String className;

    /**
     * 说明
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String tableComment;

    /**
     * 作者
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String author;

    /**
     * 邮箱
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String email;

    /**
     * 项目包名
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String packageName;

    /**
     * 项目版本号
     */
    @Size(max = 20, groups = QueryGroup.class)
    private String version;

    /**
     * 生成方式（0：zip压缩包，1：自定义目录）
     */
    private Integer generatorType;

    /**
     * 后端生成路径
     */
    @Size(max = 300, groups = QueryGroup.class)
    private String backendPath;

    /**
     * 前端生成路径
     */
    @Size(max = 300, groups = QueryGroup.class)
    private String frontendPath;

    /**
     * 模块名
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String moduleName;

    /**
     * 功能名
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String functionName;

    /**
     * 表单布局（1：一列，2：两列）
     */
    private Integer formLayout;

    /**
     * 数据源ID
     */
    private Long datasourceId;

    /**
     * 基类ID
     */
    private Long baseClassId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @Size(max = 300, groups = QueryGroup.class)
    private String remark;

    /**
     * 表字段列表
     */
    private List<TableFieldVo> fieldList;

}
