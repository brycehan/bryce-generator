package com.brycehan.boot.generator.vo;

import com.brycehan.boot.generator.validator.group.QueryGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基类管理视图对象
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Getter
@Setter
public class BaseClassVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 基类编码
     */
    @Size(max = 100, groups = QueryGroup.class)
    private String code;

    /**
     * 基类包名
     */
    @Size(max = 200, groups = QueryGroup.class)
    private String packageName;

    /**
     * 基类字段，多个用英文逗号分隔
     */
    @Size(max = 500, groups = QueryGroup.class)
    private String fields;

    /**
     * 备注
     */
    @Size(max = 300, groups = QueryGroup.class)
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
