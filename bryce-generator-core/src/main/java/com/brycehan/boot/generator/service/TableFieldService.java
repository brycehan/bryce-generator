package com.brycehan.boot.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.boot.generator.dto.TableFieldDto;
import com.brycehan.boot.generator.dto.TableFieldPageDto;
import com.brycehan.boot.generator.vo.TableFieldVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.TableField;
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
     * @param idsDto 表字段删除Dto
     */
    void delete(IdsDto idsDto);

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
