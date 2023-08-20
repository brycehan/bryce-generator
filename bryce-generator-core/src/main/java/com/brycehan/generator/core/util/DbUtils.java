package com.brycehan.generator.core.util;

import com.brycehan.generator.core.config.GenDatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类
 *
 * @author Bryce Han
 * @since 2023/5/6
 */
public class DbUtils {

    /**
     * 连接超时秒数
     */
    public static final int CONNECTION_TIMEOUT_SECONDS = 5;

    /**
     * 获取数据源连接
     *
     * @param datasource 数据源
     * @return 数据库连接
     * @throws ClassNotFoundException 驱动类找不到异常
     * @throws SQLException           数据库访问出错异常
     */
    public static Connection getConnection(GenDatasource datasource) throws ClassNotFoundException, SQLException {
        DriverManager.setLoginTimeout(CONNECTION_TIMEOUT_SECONDS);
        Class.forName(datasource.getDbType().driverClass());

        return DriverManager.getConnection(datasource.getConnUrl(), datasource.getUsername(), datasource.getPassword());
    }
}
