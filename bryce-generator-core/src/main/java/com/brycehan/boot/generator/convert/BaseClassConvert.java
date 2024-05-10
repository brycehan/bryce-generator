package com.brycehan.boot.generator.convert;

import com.brycehan.boot.generator.dto.BaseClassDto;
import com.brycehan.boot.generator.vo.BaseClassVo;
import com.brycehan.boot.generator.entity.BaseClass;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 基类转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BaseClassConvert {

    BaseClassConvert INSTANCE = Mappers.getMapper(BaseClassConvert.class);

    BaseClass convert(BaseClassDto baseClassDto);

    BaseClassVo convert(BaseClass baseClass);

    List<BaseClassVo> convert(List<BaseClass> baseClassList);

}
