package com.brycehan.boot.generator.dto;

import com.brycehan.boot.generator.common.dto.BasePageDto;
import com.brycehan.boot.generator.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 代码生成表分页数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Getter
@Setter
public class TablePageDto extends BasePageDto {

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

}
