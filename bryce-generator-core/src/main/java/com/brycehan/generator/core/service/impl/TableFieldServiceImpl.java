package com.brycehan.generator.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.DeleteDto;
import com.brycehan.generator.core.common.service.impl.BaseServiceImpl;
import com.brycehan.generator.core.convert.TableFieldConvert;
import com.brycehan.generator.core.dto.TableFieldDto;
import com.brycehan.generator.core.dto.TableFieldPageDto;
import com.brycehan.generator.core.entity.FieldType;
import com.brycehan.generator.core.entity.TableField;
import com.brycehan.generator.core.enums.AutoFillEnum;
import com.brycehan.generator.core.mapper.TableFieldMapper;
import com.brycehan.generator.core.service.FieldTypeService;
import com.brycehan.generator.core.service.TableFieldService;
import com.brycehan.generator.core.vo.TableFieldVo;
import com.google.common.base.CaseFormat;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 代码生成表字段服务实现类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Service
@RequiredArgsConstructor
public class TableFieldServiceImpl extends BaseServiceImpl<TableFieldMapper, TableField> implements TableFieldService {

    private final TableFieldMapper tableFieldMapper;

    private final FieldTypeService fieldTypeService;

    @Override
    public void save(TableFieldDto tableFieldDto) {
        TableField tableField = TableFieldConvert.INSTANCE.convert(tableFieldDto);
        this.tableFieldMapper.insert(tableField);
    }

    @Override
    public void update(TableFieldDto tableFieldDto) {
        TableField tableField = TableFieldConvert.INSTANCE.convert(tableFieldDto);
        this.tableFieldMapper.updateById(tableField);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(DeleteDto deleteDto) {
        // 过滤空数据
        List<String> ids = deleteDto.getIds()
                .stream()
                .filter(StringUtils::isNotBlank)
                .toList();
        if (CollectionUtils.isEmpty(ids)) {
            throw new RuntimeException("参数无效");
        }
        // 删除
        removeByIds(ids);
    }

    @Override
    public PageResult<TableFieldVo> page(@NotNull TableFieldPageDto tableFieldPageDto) {

        IPage<TableField> page = this.tableFieldMapper.selectPage(getPage(tableFieldPageDto), getWrapper(tableFieldPageDto));

        return new PageResult<>(page.getTotal(), TableFieldConvert.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @param tableFieldPageDto 系统岗位分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<TableField> getWrapper(TableFieldPageDto tableFieldPageDto) {
        LambdaQueryWrapper<TableField> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StringUtils.isNotBlank(tableFieldPageDto.getProjectName()), TableField::getProjectName, tableFieldPageDto.getProjectName());
        return wrapper;
    }

    @Override
    public void initFieldList(List<TableField> tableFieldList) {
        // 字段类型映射
        Map<String, FieldType> fieldTypeMap = this.fieldTypeService.getMap();
        for (int i = 0; i < tableFieldList.size(); i++) {
            var field = tableFieldList.get(i);
            var fieldType = fieldTypeMap.get(field.getFieldType().toLowerCase());
            field.setAttrName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getFieldName().toLowerCase()));
            if (Objects.isNull(fieldType)) {
                // 没找到对应的类型，默认是Object类型
                field.setAttrType("Object");
            } else {
                field.setAttrType(fieldType.getAttrType());
                field.setPackageName(fieldType.getPackageName());
            }

            field.setAutoFill(AutoFillEnum.DEFAULT.name());
            field.setFormItem(true);
            field.setGridItem(true);
            field.setQueryType("=");
            field.setQueryFormType("text");
            field.setFormItemType("text");
            field.setFormRequired(false);
            field.setSort(i);
        }
    }

    @Override
    public List<TableField> getByTableId(Long tableId) {
        LambdaQueryWrapper<TableField> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TableField::getTableId, tableId);
        queryWrapper.orderByAsc(TableField::getSort);
        return this.tableFieldMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTableField(Long tableId, List<TableField> tableFieldList) {
        // 更新排序
        for (int i = 0; i < tableFieldList.size(); i++) {
            TableField field = tableFieldList.get(i);
            field.setSort(i);
            this.tableFieldMapper.updateById(field);
        }
    }
}
