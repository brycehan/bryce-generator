package com.brycehan.generator.core.util;

import cn.hutool.core.util.StrUtil;
import com.brycehan.generator.core.config.DbType;
import com.brycehan.generator.core.config.GenDatasource;
import com.brycehan.generator.core.config.query.DbQuery;
import com.brycehan.generator.core.entity.Table;
import com.brycehan.generator.core.entity.TableField;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
        List<TableField> tableFieldList = Lists.newArrayList();

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
                field.setPrimaryKey(StringUtils.isNotBlank(key) && "PRI".equalsIgnoreCase(key));
                tableFieldList.add(field);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return tableFieldList;
    }

    /**
     * 获取模块名
     *
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String tableName) {
        return StrUtil.subBetween(tableName, "brc_", "_");
    }

    /**
     * 获取前端函数名
     *
     * @param tableName 表名
     * @return 功能名
     */
    public static String getFunctionName(String tableName) {
        String functionName = StrUtil.subAfter(tableName, "_", true);
        if (StringUtils.isBlank(functionName)) {
            functionName = tableName;
        }
        return functionName;
    }

}
