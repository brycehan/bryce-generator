package com.brycehan.generator.core.config;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author Bryce Han
 * @since 2023/5/5
 */
@Getter
@Accessors(fluent = true)
public enum DbType {

    MySQL("com.mysql.cj.jdbc.Driver"),
    PostgreSQL("org.postgresql.Driver"),
    Oracle("oracle.jdbc.driver.OracleDriver"),

    SQLServer("com.microsoft.sqlserver.jdbc.SQLServerDriver"),

    DM("dm.jdbc.driver.DmDriver"),

    Clickhouse("com.clickhouse.jdbc.ClickHouseDriver"),

    Other(null);

    /**
     * 驱动类
     */
    private final String driverClass;

    DbType(String driverClass) {
        this.driverClass = driverClass;
    }

    /**
     * 获取数据库类型
     *
     * @param dbType 数据库类型字符串
     * @return 数据库枚举实例
     */
    public static DbType dbType(String dbType) {
        for (DbType type : DbType.values()) {
            if (type.name().equalsIgnoreCase(dbType)) {
                return type;
            }
        }
        return DbType.Other;
    }
}
