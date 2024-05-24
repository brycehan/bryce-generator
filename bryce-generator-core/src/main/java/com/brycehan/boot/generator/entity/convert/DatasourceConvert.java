package com.brycehan.boot.generator.entity.convert;

import com.brycehan.boot.generator.entity.dto.DatasourceDto;
import com.brycehan.boot.generator.entity.vo.DatasourceVo;
import com.brycehan.boot.generator.entity.po.Datasource;
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
