package com.brycehan.generator.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.brycehan.generator.core.common.PageResult;
import com.brycehan.generator.core.common.dto.IdsDto;
import com.brycehan.generator.core.common.service.impl.BaseServiceImpl;
import com.brycehan.generator.core.config.GenDatasource;
import com.brycehan.generator.core.config.GeneratorProperties;
import com.brycehan.generator.core.config.template.GeneratorConfig;
import com.brycehan.generator.core.config.template.GeneratorContent;
import com.brycehan.generator.core.convert.TableConvert;
import com.brycehan.generator.core.dto.TableDto;
import com.brycehan.generator.core.dto.TablePageDto;
import com.brycehan.generator.core.entity.BaseClass;
import com.brycehan.generator.core.entity.Table;
import com.brycehan.generator.core.entity.TableField;
import com.brycehan.generator.core.enums.FormLayoutEnum;
import com.brycehan.generator.core.enums.GeneratorTypeEnum;
import com.brycehan.generator.core.mapper.TableMapper;
import com.brycehan.generator.core.service.BaseClassService;
import com.brycehan.generator.core.service.DatasourceService;
import com.brycehan.generator.core.service.TableFieldService;
import com.brycehan.generator.core.service.TableService;
import com.brycehan.generator.core.util.TableUtils;
import com.brycehan.generator.core.vo.TableVo;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 代码生成表服务实现类
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@Slf4j
@Service
@EnableConfigurationProperties(GeneratorProperties.class)
@RequiredArgsConstructor
public class TableServiceImpl extends BaseServiceImpl<TableMapper, Table> implements TableService {

    private final DatasourceService datasourceService;

    private final TableFieldService tableFieldService;

    private final BaseClassService baseClassService;

    private final GeneratorConfig generatorConfig;

    private final GeneratorProperties generatorProperties;

    @Override
    public void save(TableDto tableDto) {
        Table table = TableConvert.INSTANCE.convert(tableDto);
        this.baseMapper.insert(table);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(TableDto tableDto) {
        Table table = TableConvert.INSTANCE.convert(tableDto);
        this.baseMapper.updateById(table);

        // 更新字段基类字段信息
        List<TableField> fieldList = this.tableFieldService.getByTableId(tableDto.getId());
        if(table.getBaseClassId() == null) {
            fieldList.forEach(tableField -> tableField.setBaseField(false));
        } else {
            // 有基类时
            BaseClass baseClass = this.baseClassService.getById(tableDto.getBaseClassId());
            if(baseClass != null) {
                // 基类字段
                String[] fields = baseClass.getFields().split(",");

                // 标注为基类字段
                fieldList.forEach(tableField -> tableField.setBaseField(ArrayUtils.contains(fields, tableField.getFieldName())));
            }
        }

        // 更新列数据
        this.tableFieldService.updateBatchById(fieldList);
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
        // 删除对应字段数据
        this.tableFieldService.remove(new LambdaQueryWrapper<TableField>().in(TableField::getTableId, ids));
    }

    @Override
    public PageResult<TableVo> page(@NotNull TablePageDto tablePageDto) {

        IPage<Table> page = this.baseMapper.selectPage(getPage(tablePageDto), getWrapper(tablePageDto));

        return new PageResult<>(page.getTotal(), TableConvert.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @param tablePageDto 系统岗位分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<Table> getWrapper(TablePageDto tablePageDto) {
        LambdaQueryWrapper<Table> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(tablePageDto.getTableName()), Table::getTableName, tablePageDto.getTableName());
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tableImport(Long datasourceId, String tableName) {
        // 初始化配置信息
        GenDatasource datasource = datasourceService.get(datasourceId);

        // 查询表是否存在
        Table table = this.getByTableName(tableName);
        if (table != null) {
            throw new RuntimeException(tableName.concat("已存在"));
        }

        // 从数据库获取表信息
        table = TableUtils.getTable(datasource, tableName);
        // 代码生成器信息
        GeneratorContent generatorContent = generatorConfig.getGeneratorContent();

        // 保存表信息
        table.setPackageName(generatorContent.getProject().getPackageName());
        table.setVersion(generatorContent.getProject().getVersion());
        table.setBackendPath(generatorContent.getProject().getBackendPath());
        table.setFrontendPath(generatorContent.getProject().getFrontendPath());
        table.setAuthor(generatorContent.getDeveloper().getAuthor());
        table.setFormLayout(FormLayoutEnum.ONE.value());
        table.setGeneratorType(GeneratorTypeEnum.ZIP_DOWNLOAD.value());
        table.setClassName(TableUtils.getClassName(tableName, generatorProperties.getTablePrefix()));
        table.setModuleName(TableUtils.getModuleName(tableName, generatorProperties.getTablePrefix()));
        table.setFunctionName(TableUtils.getFunctionName(tableName, generatorProperties.getTablePrefix()));
        table.setTableComment(TableUtils.getTableComment(table.getTableComment()));

        // 默认有基类
        Optional<BaseClass> optionalBaseClass = this.baseClassService.list().stream().findFirst();
        if(optionalBaseClass.isPresent()) {
            table.setBaseClassId(optionalBaseClass.get().getId());
        }

        table.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(table);

        // 获取数据库表字段数据
        List<TableField> tableFieldList = TableUtils.getTableFieldList(datasource, table.getId(), table.getTableName());

        // 初始化列数据
        this.tableFieldService.initFieldList(tableFieldList);

        // 初始化基类字段
        if(optionalBaseClass.isPresent()) {
            // 基类字段
            String[] fields = optionalBaseClass.get().getFields().split(",");

            // 标注为基类字段
            for (TableField field : tableFieldList) {
                if (ArrayUtils.contains(fields, field.getFieldName())) {
                    field.setBaseField(true);
                }
            }
        }

        // 保存列数据
        this.tableFieldService.saveBatch(tableFieldList);

        // 释放数据源
        try {
            datasource.getConnection().close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Table getByTableName(String tableName) {
        LambdaQueryWrapper<Table> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper = queryWrapper.eq(Table::getTableName, tableName)
                .last("limit 1");

        return this.baseMapper.selectOne(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sync(Long id) {
        Table table = this.baseMapper.selectById(id);

        // 初始化配置信息
        GenDatasource datasource = this.datasourceService.get(table.getDatasourceId());

        // 从数据库获取表字段列表
        List<TableField> dbTableFieldList = TableUtils.getTableFieldList(datasource, table.getId(), table.getTableName());
        if (CollectionUtils.isEmpty(dbTableFieldList)) {
            throw new RuntimeException("同步失败，请检查数据库表：".concat(table.getTableName()));
        }

        List<String> dbTableFieldNameList = dbTableFieldList.stream()
                .map(TableField::getFieldName).toList();
        // 表字段列表
        List<TableField> tableFieldList = this.tableFieldService.getByTableId(id);
        Map<String, TableField> tableFieldMap = tableFieldList.stream()
                .collect(Collectors.toMap(TableField::getFieldName, Function.identity()));

        // 初始化字段数据
        this.tableFieldService.initFieldList(dbTableFieldList);

        // 同步表结构字段
        dbTableFieldList.forEach(tableField -> {
            // 新增字段
            if (!tableFieldMap.containsKey(tableField.getFieldName())) {
                this.tableFieldService.save(tableField);
                return;
            }

            // 修改字段
            TableField updateField = tableFieldMap.get(tableField.getFieldName());
            updateField.setPrimaryKey(tableField.isPrimaryKey());
            updateField.setFieldComment(tableField.getFieldComment());
            updateField.setFieldType(tableField.getFieldType());
            updateField.setAttrType(tableField.getAttrType());

            this.tableFieldService.updateById(updateField);
        });

        // 删除数据库表中没有的字段
        List<TableField> deleteFieldList = tableFieldList.stream()
                .filter(tableField -> !dbTableFieldNameList.contains(tableField.getFieldName()))
                .toList();
        if (!CollectionUtils.isEmpty(deleteFieldList)) {
            List<Long> deleteIds = deleteFieldList.stream()
                    .map(TableField::getId).toList();
            this.tableFieldService.removeBatchByIds(deleteIds);
        }
    }
}
