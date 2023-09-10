package com.brycehan.generator.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.generator.core.entity.FieldType;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 字段类型管理Mapper接口
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Mapper
public interface FieldTypeMapper extends BaseMapper<FieldType> {

    /**
     * 根据表ID查询包名列表
     *
     * @param tableId 表ID
     * @return 包名列表
     */
    Set<String> getPackageNameByTableId(Long tableId, Long baseClassId);

}
