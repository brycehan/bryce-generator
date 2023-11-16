package com.brycehan.boot.generator.common.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 排序项Dto 数据传输对象
 *
 * @author Bryce Han
 * @since 2021/8/31
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class OrderItemDto implements Serializable {

    /**
     * 需要进行排序的字段
     */
    @NotEmpty(message = "排序的字段不能为空")
    private String column;

    /**
     * 是否正序排列，默认 true
     */
    private Boolean asc;

}
