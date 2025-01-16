package com.brycehan.boot.generator.common.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.NamingCase;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础分页 DTO 数据传输对象
 *
 * @author Bryce Han
 * @since 2021/8/31
 */
@Data
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

    /**
     * 获取分页对象
     *
     * @return 分页对象
     */
    public  <T> IPage<T> toPage() {
        Page<T> page = new Page<>(current, pageSize);

        List<OrderItem> orderItemList = new ArrayList<>();

        // 处理排序参数
        if (CollUtil.isNotEmpty(orderItems)) {
            // 驼峰转下划线命名
            orderItems.forEach(orderItem -> orderItem.setColumn(NamingCase.toUnderlineCase(orderItem.getColumn())));
            orderItemList.addAll(BeanUtil.copyToList(orderItems, OrderItem.class));
        }

        // 默认按id降序排序
        if (CollUtil.isEmpty(orderItemList)) {
            orderItemList.add(OrderItem.desc("id"));
        }

        page.addOrder(orderItemList);

        return page;
    }

}
