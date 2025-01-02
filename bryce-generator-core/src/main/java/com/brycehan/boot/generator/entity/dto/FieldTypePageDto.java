package com.brycehan.boot.generator.entity.dto;

import com.brycehan.boot.generator.common.dto.BasePageDto;
import com.brycehan.boot.generator.common.validator.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 字段类型分页数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FieldTypePageDto extends BasePageDto {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 列类型
     */
    @Length(max = 50, groups = QueryGroup.class)
    private String columnType;

    /**
     * 属性类型
     */
    @Length(max = 50, groups = QueryGroup.class)
    private String attrType;

    /**
     * 属性包名
     */
    @Length(max = 200, groups = QueryGroup.class)
    private String packageName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @Length(max = 300, groups = QueryGroup.class)
    private String remark;

}
