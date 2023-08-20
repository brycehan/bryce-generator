package com.brycehan.generator.core.dto;

import com.brycehan.generator.core.common.dto.BasePageDto;
import com.brycehan.generator.core.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 数据源管理分页数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DatasourcePageDto extends BasePageDto {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 连接名称
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String connName;

    /**
     * 数据库类型
     */
    @Size(max = 50, groups = QueryGroup.class)
    private String dbType;

    /**
     * 连接地址
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String connUrl;

    /**
     * 用户名
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String username;

    /**
     * 密码
     */
    @Size(max = 50, groups = QueryGroup.class)
    private String password;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
