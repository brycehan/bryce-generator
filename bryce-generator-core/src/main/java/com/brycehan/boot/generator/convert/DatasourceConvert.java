package com.brycehan.boot.generator.convert;

import com.brycehan.boot.generator.dto.DatasourceDto;
import com.brycehan.boot.generator.vo.DatasourceVo;
import com.brycehan.boot.generator.entity.Datasource;
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
