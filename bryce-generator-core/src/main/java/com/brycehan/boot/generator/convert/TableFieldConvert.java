package com.brycehan.boot.generator.convert;

import com.brycehan.boot.generator.dto.TableFieldDto;
import com.brycehan.boot.generator.entity.TableField;
import com.brycehan.boot.generator.vo.TableFieldVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 表字段转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface TableFieldConvert {

    TableFieldConvert INSTANCE = Mappers.getMapper(TableFieldConvert.class);

    TableField convert(TableFieldDto tableFieldDto);

    List<TableField> convertDto(List<TableFieldDto> tableFieldDtoList);

    TableFieldVo convert(TableField tableField);

    List<TableFieldVo> convert(List<TableField> tableFieldList);

}
