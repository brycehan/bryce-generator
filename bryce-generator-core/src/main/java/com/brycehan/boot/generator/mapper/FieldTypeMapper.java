package com.brycehan.boot.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.boot.generator.entity.po.FieldType;

import java.util.Set;

/**
 * 字段类型管理Mapper接口
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
public interface FieldTypeMapper extends BaseMapper<FieldType> {

    /**
     * 根据表ID查询包名列表
     *
     * @param tableId 表ID
     * @param baseClassId 基类ID
     * @param type 类型（vo, dto, pageDto, entity）
     * @return 包名列表
     */
    Set<String> getPackageNameByTableId(Long tableId, Long baseClassId, String type);

}
