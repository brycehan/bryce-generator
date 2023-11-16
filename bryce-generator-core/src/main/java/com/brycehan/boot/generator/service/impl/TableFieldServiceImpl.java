package com.brycehan.boot.generator.service.impl;

import cn.hutool.core.text.NamingCase;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.brycehan.boot.generator.dto.TableFieldDto;
import com.brycehan.boot.generator.dto.TableFieldPageDto;
import com.brycehan.boot.generator.enums.AutoFillEnum;
import com.brycehan.boot.generator.service.FieldTypeService;
import com.brycehan.boot.generator.service.TableFieldService;
import com.brycehan.boot.generator.vo.TableFieldVo;
import com.brycehan.boot.generator.common.PageResult;
import com.brycehan.boot.generator.common.dto.IdsDto;
import com.brycehan.boot.generator.common.service.impl.BaseServiceImpl;
import com.brycehan.boot.generator.convert.TableFieldConvert;
import com.brycehan.boot.generator.entity.FieldType;
import com.brycehan.boot.generator.entity.TableField;
import com.brycehan.boot.generator.mapper.TableFieldMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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

    /** 自动填充添加属性列表 */
    private final List<String> autoFillInsertAttrNameList = List.of("createdUserId", "createdTime");

    /** 自动填充更新属性列表 */
    private final List<String> autoFillUpdateAttrNameList = List.of("updatedUserId", "updatedTime");

    /** 默认表单属性名忽略显示列表 */
    private final List<String> defaultFormAttrNameIgnoreList = List.of("id", "tenantId", "version", "deleted",
            "createdUserId", "createdTime", "updatedUserId", "updatedTime");

    /** 默认表单属性名必填列表 */
    private final List<String> defaultFormAttrNameRequiredList = List.of("name", "sort");

    /** 默认查询条件like属性列表 */
    private final List<String> defaultQueryLikeAttrNameList = List.of("name", "username");
    /** 默认查询条件equal属性列表 */
    private final List<String> defaultQueryEqualAttrNameList = List.of("type", "status", "orgId", "tenantId");
    /** 默认查询条件select属性列表 */
    private final List<String> defaultQuerySelectAttrNameList = List.of("type", "status");

    /** 默认列表属性名忽略显示列表 */
    private final List<String> defaultGridAttrNameIgnoreList = List.of("id", "tenantId", "version", "deleted",
            "createdUserId", "updatedUserId", "updatedTime");

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
    public PageResult<TableFieldVo> page(@NotNull TableFieldPageDto tableFieldPageDto) {

        IPage<TableField> page = this.tableFieldMapper.selectPage(getPage(tableFieldPageDto), getWrapper());

        return new PageResult<>(page.getTotal(), TableFieldConvert.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @return 查询条件Wrapper
     */
    private Wrapper<TableField> getWrapper() {
        return new LambdaQueryWrapper<>();
    }

    @Override
    public void initFieldList(List<TableField> tableFieldList) {
        // 字段类型映射
        Map<String, FieldType> fieldTypeMap = this.fieldTypeService.getMap();
        for (int i = 0; i < tableFieldList.size(); i++) {
            var field = tableFieldList.get(i);
            var fieldType = fieldTypeMap.get(field.getFieldType().toLowerCase());
            field.setAttrName(NamingCase.toCamelCase(field.getFieldName().toLowerCase()));
            if (Objects.isNull(fieldType)) {
                // 没找到对应的类型，默认是Object类型
                field.setAttrType("Object");
            } else {
                field.setAttrType(fieldType.getAttrType());
                field.setPackageName(fieldType.getPackageName());
            }
            // 字段自动填充初始化
            if(this.autoFillInsertAttrNameList.contains(field.getAttrName())){
                field.setAutoFill(AutoFillEnum.INSERT.name());
            }else if(this.autoFillUpdateAttrNameList.contains(field.getAttrName())){
                field.setAutoFill(AutoFillEnum.UPDATE.name());
            }else {
                field.setAutoFill(AutoFillEnum.DEFAULT.name());
            }

            field.setFormItemType("text");
            // 表单默认忽略的属性处理
            field.setFormItem(!this.defaultFormAttrNameIgnoreList.contains(field.getAttrName()));
            // 表单默认必填的属性处理
            field.setFormRequired(this.defaultFormAttrNameRequiredList.contains(field.getAttrName()));
            // 表单默认status属性处理
            if(field.getAttrName().equals("status")) {
                field.setFormDict("sys_status");
                field.setFormItemType("radio");
            }

            // 查询显示默认处理
            field.setQueryFormType("text");
            if(this.defaultQuerySelectAttrNameList.contains(field.getAttrName())) {
                field.setQueryType("=");
                field.setQueryFormType("select");
            }
            if(this.defaultQueryEqualAttrNameList.contains(field.getAttrName())){
                field.setQueryType("=");
                field.setQueryItem(true);
            }
            if(this.defaultQueryLikeAttrNameList.contains(field.getAttrName())) {
                field.setQueryType("like");
                field.setQueryItem(true);
            }

            // 列表显示默认忽略的属性处理
            field.setGridItem(!this.defaultGridAttrNameIgnoreList.contains(field.getAttrName()));
            // 列表默认排序处理
            if("sort".equals(field.getAttrName())) {
                field.setGridSort(true);
            }

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
