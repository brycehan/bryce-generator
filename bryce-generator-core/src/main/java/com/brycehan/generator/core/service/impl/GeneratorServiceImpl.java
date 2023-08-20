package com.brycehan.generator.core.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.brycehan.generator.core.config.GenDatasource;
import com.brycehan.generator.core.config.template.GeneratorConfig;
import com.brycehan.generator.core.config.template.GeneratorContent;
import com.brycehan.generator.core.config.template.TemplateVo;
import com.brycehan.generator.core.entity.BaseClass;
import com.brycehan.generator.core.entity.Table;
import com.brycehan.generator.core.entity.TableField;
import com.brycehan.generator.core.service.*;
import com.brycehan.generator.core.util.TemplateUtils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 生成代码服务
 *
 * @author Bryce Han
 * @since 2023/5/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

    private final DatasourceService datasourceService;

    private final FieldTypeService fieldTypeService;

    private final GeneratorConfig generatorConfig;

    private final BaseClassService baseClassService;

    private final TableService tableService;

    private final TableFieldService tableFieldService;

    @Override
    public Map<String, String> previewCode(Long tableId) {
        Map<String, String> data = new HashMap<>();
        // 数据模型
        Map<String, Object> dataModel = getDataModel(tableId);

        // 代码生成器内容
        GeneratorContent generatorContent = generatorConfig.getGeneratorContent();
        // 渲染模板并输出
        for (TemplateVo template : generatorContent.getTemplates()) {
            dataModel.put("templateName", template.getTemplateName());
            String content = TemplateUtils.getContent(template.getTemplateContent(), dataModel);
            String path = TemplateUtils.getContent(template.getGeneratorPath(), dataModel);
            data.put(path, content);
        }
        return data;
    }

    @Override
    public void downloadCode(Long tableId, ZipOutputStream zip) {
        // 数据模型
        Map<String, Object> dataModel = getDataModel(tableId);

        // 代码生成器内容
        GeneratorContent generatorContent = generatorConfig.getGeneratorContent();
        // 渲染模板并输出
        for (TemplateVo template : generatorContent.getTemplates()) {
            dataModel.put("templateName", template.getTemplateName());
            String content = TemplateUtils.getContent(template.getTemplateContent(), dataModel);
            String path = TemplateUtils.getContent(template.getGeneratorPath(), dataModel);

            // 替换为压缩包格式的路径
            path = path.replace("/Users/brycehan/generator", "bryce");
            try {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(path));
                IoUtil.writeUtf8(zip, false, content);
                zip.flush();
                zip.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException("模板写入失败：".concat(path), e);
            }

        }
    }

    @Override
    public void generatorCode(Long tableId) {
        // 数据模型
        Map<String, Object> dataModel = getDataModel(tableId);

        // 代码生成器内容
        GeneratorContent generatorContent = generatorConfig.getGeneratorContent();
        // 渲染模板并输出
        for (TemplateVo template : generatorContent.getTemplates()) {
            dataModel.put("templateName", template.getTemplateName());
            String content = TemplateUtils.getContent(template.getTemplateContent(), dataModel);
            String path = TemplateUtils.getContent(template.getGeneratorPath(), dataModel);

            FileUtil.writeUtf8String(content, path);
        }

    }

    /**
     * 获取模板引擎渲染的数据模型
     *
     * @param tableId 表ID
     * @return 数据模型
     */
    private Map<String, Object> getDataModel(Long tableId) {
        // 表信息
        Table table = this.tableService.getById(tableId);
        List<TableField> fieldList = this.tableFieldService.getByTableId(tableId);
        table.setFieldList(fieldList);

        // 数据模型
        Map<String, Object> dataModel = Maps.newHashMap();

        // 获取数据库类型
        GenDatasource datasource = this.datasourceService.get(table.getDatasourceId());
        dataModel.put("dbType", datasource.getDbType().name());

        // 项目信息
        dataModel.put("packageName", table.getPackageName());
        dataModel.put("packagePath", table.getPackageName().replace(".", File.separator));
        dataModel.put("version", table.getVersion());
        dataModel.put("moduleName", table.getModuleName());
        dataModel.put("ModuleName", StrUtil.upperFirst(table.getModuleName()));
        dataModel.put("functionName", table.getFunctionName());
        dataModel.put("FunctionName", StrUtil.upperFirst(table.getFunctionName()));
        dataModel.put("formLayout", table.getFormLayout());

        // 开发者信息
        dataModel.put("author", table.getAuthor());
        dataModel.put("date", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dataModel.put("datetime", LocalDateTime.now().format(formatter));

        // 设置字段分类
        setFieldTypeList(dataModel, table);

        // 设置基类信息
        setBaseClass(dataModel, table);

        // 导入包的列表
        List<String> importList = this.fieldTypeService.getPackageNameByTableId(table.getId());
        dataModel.put("importList", importList);

        // 表信息
        dataModel.put("tableName", table.getTableName());
        dataModel.put("tableComment", table.getTableComment());
        dataModel.put("className", table.getClassName());
        dataModel.put("entityName", table.getClassName());
        dataModel.put("fieldList", table.getFieldList());
        // 常用变量
        dataModel.put("controllerName", table.getClassName().concat("Controller"));
        dataModel.put("serviceName", table.getClassName().concat("Service"));
        dataModel.put("serviceImplName", table.getClassName().concat("ServiceImpl"));
        dataModel.put("mapperName", table.getClassName().concat("Mapper"));
        dataModel.put("convertName", table.getClassName().concat("Convert"));
        dataModel.put("entityDtoName", table.getClassName().concat("Dto"));
        dataModel.put("entityPageDtoName", table.getClassName().concat("PageDto"));
        dataModel.put("entityVoName", table.getClassName().concat("Vo"));
        String entityParam = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, table.getClassName());
        dataModel.put("entityParam", entityParam);
        dataModel.put("serviceParam", entityParam.concat("Service"));
        dataModel.put("mapperParam", entityParam.concat("Mapper"));

        // 生成路径
        dataModel.put("backendPath", table.getBackendPath());
        dataModel.put("frontendPath", table.getFrontendPath());

        return dataModel;
    }

    /**
     * 设置字段信息
     *
     * @param dataModel 数据模型
     * @param table     表
     */
    private void setFieldTypeList(Map<String, Object> dataModel, Table table) {
        // 主键列表
        List<TableField> primaryKeys = new ArrayList<>();
        // 表单列表
        List<TableField> formList = new ArrayList<>();
        // 网格列表
        List<TableField> gridList = new ArrayList<>();
        // 查询列表
        List<TableField> queryList = new ArrayList<>();
        for (TableField field : table.getFieldList()) {
            if (field.getPrimaryKey()) {
                primaryKeys.add(field);
            }
            if (field.getFormItem()) {
                formList.add(field);
            }
            if (field.getGridItem()) {
                gridList.add(field);
            }
            if (field.getQueryItem() == Boolean.TRUE) {
                queryList.add(field);
            }
        }
        dataModel.put("primaryKeys", primaryKeys);
        dataModel.put("formList", formList);
        dataModel.put("gridList", gridList);
        dataModel.put("queryList", queryList);
    }

    /**
     * 设置基类信息
     *
     * @param dataModel 数据模型
     * @param table     表
     */
    private void setBaseClass(Map<String, Object> dataModel, Table table) {
        if (table.getBaseclassId() == null) {
            for (TableField field : table.getFieldList()) {
                field.setBaseField(false);
            }
            return;
        }

        // 基类
        BaseClass baseClass = this.baseClassService.getById(table.getBaseclassId());
        baseClass.setPackageName(baseClass.getPackageName());
        dataModel.put("baseClass", baseClass);

        // 基类字段
        String[] fields = baseClass.getFields().split(",");

        // 标注为基类字段
        for (TableField field : table.getFieldList()) {
            if (ArrayUtils.contains(fields, field.getFieldName())) {
                field.setBaseField(true);
            }
        }
    }

}
