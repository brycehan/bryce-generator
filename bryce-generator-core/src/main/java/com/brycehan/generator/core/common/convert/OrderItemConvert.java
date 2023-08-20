package com.brycehan.generator.core.common.convert;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.brycehan.generator.core.common.dto.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 排序项转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface OrderItemConvert {

    OrderItemConvert INSTANCE = Mappers.getMapper(OrderItemConvert.class);

    OrderItem convert(OrderItemDto orderItemDto);

    List<OrderItem> convert(List<OrderItemDto> orderItemDtoList);

}
