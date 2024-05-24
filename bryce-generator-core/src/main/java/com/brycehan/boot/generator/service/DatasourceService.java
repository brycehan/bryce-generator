package com.brycehan.boot.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.boot.generator.config.GenDatasource;
import com.brycehan.boot.generator.entity.dto.DatasourceDto;
import com.brycehan.boot.generator.entity.dto.DatasourcePageDto;
import com.brycehan.boot.generator.entity.vo.DatasourceVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.po.Datasource;
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
     * @param idsDto 数据源删除Dto
     */
    void delete(IdsDto idsDto);

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
