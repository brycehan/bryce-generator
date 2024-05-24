package com.brycehan.boot.generator.entity.convert;

import com.brycehan.boot.generator.entity.dto.TableFieldDto;
import com.brycehan.boot.generator.entity.po.TableField;
import com.brycehan.boot.generator.entity.vo.TableFieldVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表字段转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableFieldConvert {

    TableFieldConvert INSTANCE = Mappers.getMapper(TableFieldConvert.class);

    TableField convert(TableFieldDto tableFieldDto);

    List<TableField> convertDto(List<TableFieldDto> tableFieldDtoList);

    TableFieldVo convert(TableField tableField);

    List<TableFieldVo> convert(List<TableField> tableFieldList);

}
