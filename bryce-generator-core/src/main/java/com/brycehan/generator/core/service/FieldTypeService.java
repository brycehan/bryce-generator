package com.brycehan.generator.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.dto.FieldTypeDto;
import com.brycehan.generator.core.dto.FieldTypePageDto;
import com.brycehan.generator.core.entity.FieldType;
import com.brycehan.generator.core.vo.FieldTypeVo;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字段类型管理服务类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Validated
public interface FieldTypeService extends IService<FieldType> {


    /**
     * 添加字段类型
     *
     * @param fieldTypeDto 字段类型Dto
     */
    void save(FieldTypeDto fieldTypeDto);

    /**
     * 更新字段类型
     *
     * @param fieldTypeDto 字段类型Dto
     */
    void update(FieldTypeDto fieldTypeDto);

    /**
     * 删除字段类型
     *
     * @param idsDto 字段类型删除Dto
     */
    void delete(IdsDto idsDto);

    /**
     * 字段类型分页查询信息
     *
     * @param fieldTypePageDto 字段类型分页搜索条件
     * @return 分页信息
     */
    PageResult<FieldTypeVo> page(@NotNull FieldTypePageDto fieldTypePageDto);

    /**
     * 获取字段类型Map
     *
     * @return 字段类型Map
     */
    Map<String, FieldType> getMap();

    /**
     * 根据表ID查询包名列表
     *
     * @param tableId 表ID
     * @param baseClassId 基类ID
     * @param type 类型（vo, dto, pageDto, entity）
     * @return 包名列表
     */
    Set<String> getPackageNameByTableId(Long tableId, Long baseClassId, String type);

    /**
     * 查询属性类型列表
     *
     * @return 属性类型列表
     */
    List<String> getAttrTypeList();

}
