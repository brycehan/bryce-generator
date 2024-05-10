package com.brycehan.boot.generator.convert;

import com.brycehan.boot.generator.dto.TableDto;
import com.brycehan.boot.generator.vo.TableVo;
import com.brycehan.boot.generator.entity.Table;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableConvert {

    TableConvert INSTANCE = Mappers.getMapper(TableConvert.class);

    Table convert(TableDto tableDto);

    TableVo convert(Table table);

    List<TableVo> convert(List<Table> tableList);

}
