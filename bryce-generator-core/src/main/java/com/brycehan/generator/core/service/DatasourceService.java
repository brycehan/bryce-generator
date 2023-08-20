package com.brycehan.generator.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.config.GenDatasource;
import com.brycehan.generator.core.dto.DatasourceDto;
import com.brycehan.generator.core.dto.DatasourcePageDto;
import com.brycehan.generator.core.entity.Datasource;
import com.brycehan.generator.core.vo.DatasourceVo;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * 数据源管理服务类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Validated
public interface DatasourceService extends IService<Datasource> {


    /**
     * 添加数据源
     *
     * @param datasourceDto 数据源Dto
     */
    void save(DatasourceDto datasourceDto);

    /**
     * 更新数据源
     *
     * @param datasourceDto 数据源Dto
     */
    void update(DatasourceDto datasourceDto);

    /**
     * 删除数据源
     *
     * @param deleteDto 数据源删除Dto
     */
    void delete(DeleteDto deleteDto);

    /**
     * 获取数据源配置
     *
     * @param datasourceId 数据源ID
     * @return 数据源配置
     */
    GenDatasource get(Long datasourceId);

    /**
     * 数据源分页查询信息
     *
     * @param datasourcePageDto 数据源分页搜索条件
     * @return 分页信息
     */
    PageResult<DatasourceVo> page(@NotNull DatasourcePageDto datasourcePageDto);

}
