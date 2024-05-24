package com.brycehan.boot.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.boot.generator.entity.dto.TableDto;
import com.brycehan.boot.generator.entity.dto.TablePageDto;
import com.brycehan.boot.generator.entity.vo.TableVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.po.Table;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 * 代码生成表服务类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Validated
public interface TableService extends IService<Table> {

    /**
     * 添加表
     *
     * @param tableDto 表Dto
     */
    void save(TableDto tableDto);

    /**
     * 更新表
     *
     * @param tableDto 表Dto
     */
    void update(TableDto tableDto);

    /**
     * 删除表
     *
     * @param idsDto 表删除Dto
     */
    void delete(IdsDto idsDto);

    /**
     * 表分页查询信息
     *
     * @param tablePageDto 表分页搜索条件
     * @return 分页信息
     */
    PageResult<TableVo> page(@NotNull TablePageDto tablePageDto);

    /**
     * 导入数据源表
     *
     * @param datasourceId 数据源ID
     * @param tableName    表名
     */
    void tableImport(Long datasourceId, String tableName);

    /**
     * 同步表结构
     *
     * @param id 表ID
     */
    void sync(Long id);

    /**
     * 根据表名查询表信息
     *
     * @param tableName 表名
     * @return 表信息
     */
    Table getByTableName(String tableName);

}
