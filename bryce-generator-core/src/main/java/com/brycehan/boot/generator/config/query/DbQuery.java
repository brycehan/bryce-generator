package com.brycehan.boot.generator.config.query;

import com.brycehan.boot.generator.config.DbType;

/**
 * 数据库查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public interface DbQuery {

    /**
     * 数据库类型
     *
     * @return 数据库类型
     */
    DbType dbType();

    /**
     * 表信息查询SQL
     *
     * @param tableName 表名
     * @return 表信息查询SQL
     */
    String tableSql(String tableName);

    /**
     * 表名
     *
     * @return 表名
     */
    String tableName();

    /**
     * 表注释
     *
     * @return 表注释
     */
    String tableComment();

    /**
     * 表字段信息查询SQL
     *
     * @return 表字段信息查询SQL
     */
    String tableFieldsSql();

    /**
     * 字段名
     *
     * @return 字段名
     */
    String fieldName();

    /**
     * 字段类型
     *
     * @return 字段类型
     */
    String fieldType();

    /**
     * 字段注释
     *
     * @return 字段注释
     */
    String fieldComment();

    /**
     * 主键字段
     *
     * @return 主键字段
     */
    String fieldKey();

    /**
     * 字符最大长度
     *
     * @return 该字段可存储的字符最大长度
     */
    String fieldCharacterMaximumLength();

}
