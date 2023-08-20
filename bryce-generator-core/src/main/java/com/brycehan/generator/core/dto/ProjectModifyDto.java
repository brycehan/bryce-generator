package com.brycehan.generator.core.dto;

import com.brycehan.generator.core.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    @Size(max = 100, groups = QueryGroup.class)
    private String projectName;

    /**
     * 项目标识
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String projectCode;

    /**
     * 项目包名
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String projectPackage;

    /**
     * 项目路径
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String projectPath;

    /**
     * 变更项目名称
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String modifyProjectName;

    /**
     * 变更项目标识
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String modifyProjectCode;

    /**
     * 变更项目包名
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String modifyProjectPackage;

    /**
     * 排除文件
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String exclusions;

    /**
     * 变更文件
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String modifySuffix;

    /**
     * 变更临时路径
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String modifyTmpPath;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
