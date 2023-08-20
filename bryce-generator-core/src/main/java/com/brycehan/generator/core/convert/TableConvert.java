package com.brycehan.generator.core.convert;

import com.brycehan.generator.core.dto.TableDto;
import com.brycehan.generator.core.entity.Table;
import com.brycehan.generator.core.vo.TableVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface TableConvert {

    TableConvert INSTANCE = Mappers.getMapper(TableConvert.class);

    Table convert(TableDto tableDto);

    TableVo convert(Table table);

    List<TableVo> convert(List<Table> tableList);

}
