package com.brycehan.generator.core.convert;

import com.brycehan.generator.core.dto.DatasourceDto;
import com.brycehan.generator.core.entity.Datasource;
import com.brycehan.generator.core.vo.DatasourceVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 数据源转换器
 *
 * @author Bryce Han
 * @since 2023/4/13
 */
@Mapper
public interface DatasourceConvert {

    DatasourceConvert INSTANCE = Mappers.getMapper(DatasourceConvert.class);

    Datasource convert(DatasourceDto datasourceDto);

    DatasourceVo convert(Datasource datasource);

    List<DatasourceVo> convert(List<Datasource> datasourceList);

}
