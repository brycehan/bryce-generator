package com.brycehan.generator.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.common.service.impl.BaseServiceImpl;
import com.brycehan.generator.core.convert.FieldTypeConvert;
import com.brycehan.generator.core.dto.FieldTypeDto;
import com.brycehan.generator.core.dto.FieldTypePageDto;
import com.brycehan.generator.core.entity.FieldType;
import com.brycehan.generator.core.mapper.FieldTypeMapper;
import com.brycehan.generator.core.service.FieldTypeService;
import com.brycehan.generator.core.vo.FieldTypeVo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * 字段类型管理服务实现类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Service
@RequiredArgsConstructor
public class FieldTypeServiceImpl extends BaseServiceImpl<FieldTypeMapper, FieldType> implements FieldTypeService {

    private final FieldTypeMapper fieldTypeMapper;

    @Override
    public void save(FieldTypeDto fieldTypeDto) {
        FieldType fieldType = FieldTypeConvert.INSTANCE.convert(fieldTypeDto);
        this.fieldTypeMapper.insert(fieldType);
    }

    @Override
    public void update(FieldTypeDto fieldTypeDto) {
        FieldType fieldType = FieldTypeConvert.INSTANCE.convert(fieldTypeDto);
        this.fieldTypeMapper.updateById(fieldType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(IdsDto idsDto) {
        // 过滤空数据
        List<Long> ids = idsDto.getIds()
                .stream()
                .filter(Objects::nonNull)
                .toList();
        if (CollectionUtils.isEmpty(ids)) {
            throw new RuntimeException("参数无效");
        }
        // 删除
        removeByIds(ids);
    }

    @Override
    public PageResult<FieldTypeVo> page(@NotNull FieldTypePageDto fieldTypePageDto) {

        IPage<FieldType> page = this.fieldTypeMapper.selectPage(getPage(fieldTypePageDto), getWrapper(fieldTypePageDto));

        return new PageResult<>(page.getTotal(), FieldTypeConvert.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @param fieldTypePageDto 系统岗位分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<FieldType> getWrapper(FieldTypePageDto fieldTypePageDto) {
        LambdaQueryWrapper<FieldType> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(fieldTypePageDto.getColumnType()), FieldType::getColumnType, fieldTypePageDto.getColumnType());
        wrapper.like(StringUtils.isNotBlank(fieldTypePageDto.getAttrType()), FieldType::getAttrType, fieldTypePageDto.getAttrType());
        return wrapper;
    }

    @Override
    public Map<String, FieldType> getMap() {
        List<FieldType> list = this.fieldTypeMapper.selectList(null);
        Map<String, FieldType> map = new LinkedHashMap<>(list.size());
        for (FieldType fieldType : list) {
            map.put(fieldType.getColumnType().toLowerCase(), fieldType);
        }

        return map;
    }

    @Override
    public Set<String> getPackageNameByTableId(Long tableId, Long baseClassId) {
        return this.fieldTypeMapper.getPackageNameByTableId(tableId, baseClassId);
    }

    @Override
    public List<String> getAttrTypeList() {
        LambdaQueryWrapper<FieldType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(FieldType::getAttrType)
                .orderByDesc(FieldType::getAttrType);

        return this.listObjs(queryWrapper, Object::toString).stream()
                .distinct().toList();
    }
}
