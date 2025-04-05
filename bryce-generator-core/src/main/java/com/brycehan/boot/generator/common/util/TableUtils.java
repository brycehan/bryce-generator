package com.brycehan.boot.generator.common.util;

import cn.hutool.core.text.NamingCase;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.brycehan.boot.generator.config.query.DbQuery;
import com.brycehan.boot.generator.config.DbType;
import com.brycehan.boot.generator.config.GenDatasource;
import com.brycehan.boot.generator.entity.po.Table;
import com.brycehan.boot.generator.entity.po.TableField;
import lombok.extern.slf4j.Slf4j;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 表工具类
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
@Slf4j
public class TableUtils {

    /**
     * 根据数据源，获取全部数据表
     *
     * @param datasource 数据源
     * @return 全部数据表列表
     */
    public static List<Table> getTableList(GenDatasource datasource) {
        List<Table> tableList = new ArrayList<>();
        DbQuery query = datasource.getDbQuery();
        // 查询数据
        try {
            PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(query.tableSql(null));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Table table = new Table();
                table.setTableName(resultSet.getString(query.tableName()));
                table.setTableComment(resultSet.getString(query.tableComment()));
                table.setDatasourceId(datasource.getId());
                tableList.add(table);
            }
            datasource.getConnection().close();
        } catch (SQLException e) {
            log.error("TableUtils.getTableList, {}", e.getMessage(), e);
        }

        return tableList;
    }

    /**
     * 根据数据源获取指定数据库表
     *
     * @param datasource 数据源
     * @param tableName  表名
     * @return 表实体
     */
    public static Table getTable(GenDatasource datasource, String tableName) {
        DbQuery query = datasource.getDbQuery();
        try {
            PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(query.tableSql(tableName));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Table table = new Table();
                table.setTableName(resultSet.getString(query.tableName()));
                table.setTableComment(resultSet.getString(query.tableComment()));
                table.setDatasourceId(datasource.getId());
                return table;
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        throw new RuntimeException("数据库表".concat(tableName).concat("不存在"));
    }

    /**
     * 获取表字段列表
     *
     * @param datasource 数据源
     * @param tableId    表ID
     * @param tableName  表名
     * @return 表字段列表
     */
    public static List<TableField> getTableFieldList(GenDatasource datasource, Long tableId, String tableName) {
        List<TableField> tableFieldList = new ArrayList<>();

        DbQuery query = datasource.getDbQuery();
        String tableFieldsSql = query.tableFieldsSql();
        try {
            if (datasource.getDbType() == DbType.Oracle) {
                DatabaseMetaData metaData = datasource.getConnection().getMetaData();
                tableFieldsSql = String.format(tableFieldsSql.replace("#schema", metaData.getUserName()), tableName);
            } else {
                tableFieldsSql = String.format(tableFieldsSql, tableName);
            }
            PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(tableFieldsSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TableField field = new TableField();
                field.setTableId(tableId);
                field.setFieldName(resultSet.getString(query.fieldName()));
                String fieldType = resultSet.getString(query.fieldType());
                if (fieldType.contains(" ")) {
                    fieldType = fieldType.substring(0, fieldType.indexOf(" "));
                }
                field.setFieldType(fieldType);
                field.setFieldComment(resultSet.getString(query.fieldComment()));
                String key = resultSet.getString(query.fieldKey());
                field.setPrimaryKey(StrUtil.isNotBlank(key) && "PRI".equalsIgnoreCase(key));
                field.setCharacterMaximumLength(resultSet.getLong(query.fieldCharacterMaximumLength()));
                tableFieldList.add(field);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return tableFieldList;
    }

    /**
     * 获取类名
     *
     * @param tableName 表名
     * @return 类名
     */
    public static String getClassName(String tableName, String tablePrefix) {
        String className = NamingCase.toPascalCase(tableName);
        // 去除前缀
        String[] prefixArray = tablePrefix.split(",");
        if(ArrayUtil.isNotEmpty(prefixArray)){
            for (String  prefix : prefixArray) {
                if(tableName.startsWith(prefix)) {
                    className = NamingCase.toPascalCase(StrUtil.subAfter(tableName, prefix, false));
                    break;
                }
            }
        }

        return className;
    }

    /**
     * 获取模块名称
     *
     * @param tableName 表名
     * @return 模块名称
     */
    public static String getModuleName(String tableName, String tablePrefix) {

        String moduleName = StrUtil.subBefore(tableName, "_", false);
        // 获取模块名称
        String[] prefixArray = tablePrefix.split(",");
        if(ArrayUtil.isNotEmpty(prefixArray)){
            for (String  prefix : prefixArray) {
                if(tableName.startsWith(prefix)) {
                    moduleName = StrUtil.subBetween(tableName, prefix, "_");
                    break;
                }
            }
        }

        if("sys".equals(moduleName) || StrUtil.isEmpty(moduleName)){
            moduleName = "system";
        }
        return moduleName;
    }

    /**
     * 获取前端函数名
     *
     * @param tableName 表名
     * @return 功能名称
     */
    public static String getFunctionName(String tableName, String tablePrefix) {
        String functionName = NamingCase.toPascalCase(tableName);
        // 去除前缀
        String[] prefixArray = tablePrefix.split(",");
        if(ArrayUtil.isNotEmpty(prefixArray)){
            for (String  prefix : prefixArray) {
                if(tableName.startsWith(prefix)) {
                    functionName = StrUtil.subAfter(tableName, prefix, false);
                    break;
                }
            }
        }
        // 去除模块前缀
        if(functionName.contains("_") && functionName.length() > 1){
            functionName = StrUtil.subAfter(functionName, "_", false);
        }
        return StrUtil.toCamelCase(functionName);
    }

    /**
     * 获取表说明
     *
     * @param rawTableComment 表说明
     * @return 功能名称
     */
    public static String getTableComment(String rawTableComment) {
        if(rawTableComment.endsWith("表")){
            return rawTableComment.substring(0, rawTableComment.length() - 1);
        }
        return rawTableComment;
    }

}
