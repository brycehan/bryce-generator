package com.brycehan.boot.generator.entity.dto;

import com.brycehan.boot.generator.common.validator.QueryGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * 基类管理分页数据传输对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Getter
@Setter
public class BaseClassDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 基类编码
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String code;

    /**
     * 基类包名
     */
    @Length(max = 200, groups = QueryGroup.class)
    private String packageName;

    /**
     * 基类字段，多个用英文逗号分隔
     */
    @Length(max = 500, groups = QueryGroup.class)
    private String fields;

    /**
     * 备注
     */
    @Length(max = 300, groups = QueryGroup.class)
    private String remark;

}
