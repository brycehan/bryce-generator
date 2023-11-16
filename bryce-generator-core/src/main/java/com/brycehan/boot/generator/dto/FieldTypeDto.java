package com.brycehan.boot.generator.dto;

import com.brycehan.boot.generator.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字段类型管理分页数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
public class FieldTypeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 列类型
     */
    @Size(max = 50, groups = QueryGroup.class)
    private String columnType;

    /**
     * 属性类型
     */
    @Size(max = 50, groups = QueryGroup.class)
    private String attrType;

    /**
     * 属性包名
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String packageName;

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
