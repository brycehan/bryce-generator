package com.brycehan.boot.generator.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
import com.brycehan.boot.generator.common.validator.QueryGroup;
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
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectName;

    /**
     * 项目标识
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectCode;

    /**
     * 项目标识缩写
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectCodeAbbreviate;

    /**
     * 项目包名
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectPackage;

    /**
     * 项目路径
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String projectPath;

    /**
     * 变更项目名称
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyProjectName;

    /**
     * 变更项目标识
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyProjectCode;

    /**
     * 变更项目标识缩写
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String modifyProjectCodeAbbreviate;

    /**
     * 变更项目包名
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyProjectPackage;

    /**
     * 排除文件
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String exclusions;

    /**
     * 变更文件
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifySuffix;

    /**
     * 变更临时路径
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String modifyTmpPath;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Null(groups = {SaveGroup.class, UpdateGroup.class})
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
