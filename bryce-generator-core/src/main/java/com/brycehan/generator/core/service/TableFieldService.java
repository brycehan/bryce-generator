package com.brycehan.generator.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.dto.TableFieldDto;
import com.brycehan.generator.core.dto.TableFieldPageDto;
import com.brycehan.generator.core.entity.TableField;
import com.brycehan.generator.core.vo.TableFieldVo;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 代码生成表字段服务类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Validated
public interface TableFieldService extends IService<TableField> {

    /**
     * 添加表字段
     *
     * @param tableFieldDto 表字段Dto
     */
    void save(TableFieldDto tableFieldDto);

    /**
     * 更新表字段
     *
     * @param tableFieldDto 表字段Dto
     */
    void update(TableFieldDto tableFieldDto);

    /**
     * 删除表字段
     *
     * @param deleteDto 表字段删除Dto
     */
    void delete(DeleteDto deleteDto);

    /**
     * 表字段分页查询信息
     *
     * @param tableFieldPageDto 表字段分页搜索条件
     * @return 分页信息
     */
    PageResult<TableFieldVo> page(@NotNull TableFieldPageDto tableFieldPageDto);

    /**
     * 初始化字段数据
     *
     * @param tableFieldList 字段列表
     */
    void initFieldList(List<TableField> tableFieldList);

    /**
     * 根据表ID获取表字段列表
     *
     * @param tableId 表ID
     * @return 表字段列表
     */
    List<TableField> getByTableId(Long tableId);

    /**
     * 修改表字段数据
     *
     * @param tableId        表ID
     * @param tableFieldList 字段列表
     */
    void updateTableField(Long tableId, List<TableField> tableFieldList);
}
