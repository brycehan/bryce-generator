package com.brycehan.generator.core.convert;

import com.brycehan.generator.core.dto.FieldTypeDto;
import com.brycehan.generator.core.entity.FieldType;
import com.brycehan.generator.core.vo.FieldTypeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字段类型转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface FieldTypeConvert {

    FieldTypeConvert INSTANCE = Mappers.getMapper(FieldTypeConvert.class);

    FieldType convert(FieldTypeDto fieldTypeDto);

    FieldTypeVo convert(FieldType fieldType);

    List<FieldTypeVo> convert(List<FieldType> fieldTypeList);

}
