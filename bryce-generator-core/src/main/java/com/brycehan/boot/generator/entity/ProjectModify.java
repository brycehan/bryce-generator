package com.brycehan.boot.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
import com.brycehan.boot.generator.validator.group.SaveGroup;
import com.brycehan.boot.generator.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 项目名变更
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@TableName("brc_gen_project_modify")
public class ProjectModify implements Serializable {

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
     * 项目名称
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectName;

    /**
     * 项目标识
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectCode;

    /**
     * 项目包名
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectPackage;

    /**
     * 项目路径
     */
    @Size(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectPath;

    /**
     * 变更项目名称
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyProjectName;

    /**
     * 变更项目标识
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyProjectCode;

    /**
     * 变更项目包名
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyProjectPackage;

    /**
     * 排除文件
     */
    @Size(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String exclusions;

    /**
     * 变更文件
     */
    @Size(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifySuffix;

    /**
     * 变更临时路径
     */
    @Size(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyTmpPath;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Null(groups = {SaveGroup.class, UpdateGroup.class})
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
