package com.brycehan.generator.core.convert;

import com.brycehan.generator.core.dto.BaseClassDto;
import com.brycehan.generator.core.entity.BaseClass;
import com.brycehan.generator.core.vo.BaseClassVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 基类转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface BaseClassConvert {

    BaseClassConvert INSTANCE = Mappers.getMapper(BaseClassConvert.class);

    BaseClass convert(BaseClassDto baseClassDto);

    BaseClassVo convert(BaseClass baseClass);

    List<BaseClassVo> convert(List<BaseClass> baseClassList);

}
