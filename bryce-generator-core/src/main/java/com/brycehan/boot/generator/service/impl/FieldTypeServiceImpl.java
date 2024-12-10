package com.brycehan.boot.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.brycehan.boot.generator.entity.dto.FieldTypeDto;
import com.brycehan.boot.generator.entity.dto.FieldTypePageDto;
import com.brycehan.boot.generator.entity.vo.FieldTypeVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.entity.convert.FieldTypeConvert;
import com.brycehan.boot.generator.entity.po.FieldType;
import com.brycehan.boot.generator.mapper.FieldTypeMapper;
import com.brycehan.boot.generator.service.FieldTypeService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import cn.hutool.core.util.StrUtil;
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
public class FieldTypeServiceImpl extends ServiceImpl<FieldTypeMapper, FieldType> implements FieldTypeService {

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

        IPage<FieldType> page = this.fieldTypeMapper.selectPage(fieldTypePageDto.toPage(), getWrapper(fieldTypePageDto));

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
        wrapper.like(StrUtil.isNotBlank(fieldTypePageDto.getColumnType()), FieldType::getColumnType, fieldTypePageDto.getColumnType());
        wrapper.like(StrUtil.isNotBlank(fieldTypePageDto.getAttrType()), FieldType::getAttrType, fieldTypePageDto.getAttrType());
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
    public Set<String> getPackageNameByTableId(Long tableId, Long baseClassId, String type) {
        return this.fieldTypeMapper.getPackageNameByTableId(tableId, baseClassId, type);
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
