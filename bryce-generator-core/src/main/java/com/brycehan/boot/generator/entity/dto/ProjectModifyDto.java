package com.brycehan.boot.generator.entity.dto;

import com.brycehan.boot.generator.common.validator.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 项目名变更数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
public class ProjectModifyDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 项目名称
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String projectName;

    /**
     * 项目标识
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String projectCode;

    /**
     * 项目标识缩写
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String projectCodeAbbreviate;

    /**
     * 项目包名
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String projectPackage;

    /**
     * 项目路径
     */
    @Length(max = 200, groups = QueryGroup.class)
    private String projectPath;

    /**
     * 变更项目名称
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String modifyProjectName;

    /**
     * 变更项目标识
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String modifyProjectCode;

    /**
     * 变更项目标识缩写
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String modifyProjectCodeAbbreviate;

    /**
     * 变更项目包名
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String modifyProjectPackage;

    /**
     * 排除文件
     */
    @Length(max = 200, groups = QueryGroup.class)
    private String exclusions;

    /**
     * 变更文件
     */
    @Length(max = 200, groups = QueryGroup.class)
    private String modifySuffix;

    /**
     * 变更临时路径
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String modifyTmpPath;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
