package com.brycehan.boot.generator.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.*;
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
 * 数据源管理
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@TableName("brc_gen_datasource")
public class Datasource implements Serializable {

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
     * 连接名称
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String connName;

    /**
     * 数据库类型
     */
    @Length(max = 50, groups = {SaveGroup.class, UpdateGroup.class})
    private String dbType;

    /**
     * 连接地址
     */
    @Length(max = 200, groups = {SaveGroup.class, UpdateGroup.class})
    private String connUrl;

    /**
     * 用户名
     */
    @Length(max = 100, groups = {SaveGroup.class, UpdateGroup.class})
    private String username;

    /**
     * 密码
     */
    @Length(max = 50, groups = {SaveGroup.class, UpdateGroup.class})
    private String password;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Null(groups = {SaveGroup.class, UpdateGroup.class})
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
