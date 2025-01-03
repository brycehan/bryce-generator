package com.brycehan.boot.generator.config;

import com.brycehan.boot.generator.common.util.DbUtils;
import com.brycehan.boot.generator.common.validator.QueryGroup;
import com.brycehan.boot.generator.config.query.*;
import com.brycehan.boot.generator.entity.po.Datasource;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * 代码生成器数据源
 *
 * @author Bryce Han
 * @since 2023/5/6
 */
@Slf4j
@Data
public class GenDatasource implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 数据源ID
     */
    private Long id;

    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 连接地址
     */
    @Length(max = 200, groups = QueryGroup.class)
    private String connUrl;

    /**
     * 用户名
     */
    @Length(max = 100, groups = QueryGroup.class)
    private String username;

    /**
     * 密码
     */
    @Length(max = 50, groups = QueryGroup.class)
    private String password;

    /**
     * 数据库查询
     */
    private DbQuery dbQuery;

    /**
     * 数据库连接
     */
    private Connection connection;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    public GenDatasource(Datasource datasource) {
        this.id = datasource.getId();
        this.dbType = DbType.dbType(datasource.getDbType());
        this.connUrl = datasource.getConnUrl();
        this.username = datasource.getUsername();
        this.password = datasource.getPassword();
        setDbQuery(this.dbType);

        try {
            this.connection = DbUtils.getConnection(this);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public GenDatasource(Connection connection) {
        this.id = 0L;
        try {
            this.dbType = DbType.dbType(connection.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        setDbQuery(this.dbType);
        this.connection = connection;
    }

    private void setDbQuery(DbType dbType) {
        if (dbType == DbType.MySQL) {
            this.dbQuery = new MySQLDbQuery();
        } else if (dbType == DbType.PostgreSQL) {
            this.dbQuery = new PostgreSQLDbQuery();
        } else if (dbType == DbType.Oracle) {
            this.dbQuery = new OracleDbQuery();
        } else if (dbType == DbType.SQLServer) {
            this.dbQuery = new SQLServerDbQuery();
        } else if (dbType == DbType.DM) {
            this.dbQuery = new DmDbQuery();
        } else if (dbType == DbType.Clickhouse) {
            this.dbQuery = new ClickHouseDbQuery();
        }
    }

}
