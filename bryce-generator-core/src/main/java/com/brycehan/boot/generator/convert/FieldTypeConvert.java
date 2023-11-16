package com.brycehan.boot.generator.convert;

import com.brycehan.boot.generator.dto.FieldTypeDto;
import com.brycehan.boot.generator.vo.FieldTypeVo;
import com.brycehan.boot.generator.entity.FieldType;
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
