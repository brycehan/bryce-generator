package com.brycehan.generator.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.generator.core.entity.Datasource;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源管理Mapper接口
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Mapper
public interface DatasourceMapper extends BaseMapper<Datasource> {

}
