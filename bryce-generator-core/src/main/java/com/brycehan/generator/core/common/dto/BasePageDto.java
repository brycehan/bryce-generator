package com.brycehan.generator.core.common.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.List;

/**
 * 基础分页 DTO 数据传输对象
 *
 * @author Bryce Han
 * @since 2021/8/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BasePageDto implements Serializable {

    /**
     * 起始页数，从1开始计算
     */
    @Range(min = 1)
    @NotNull(message = "页码不能为空")
    private Integer current;

    /**
     * 每页条数
     */
    @Range(min = 1)
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    /**
     * 排序项
     */
    @Valid
    private List<OrderItemDto> orderItems;

}
