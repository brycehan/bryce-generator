package com.brycehan.boot.generator.config.query;


import com.brycehan.boot.generator.config.DbType;
import org.apache.commons.lang3.StringUtils;

/**
 * ClickHouse查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public class ClickHouseDbQuery implements DbQuery {

    @Override
    public DbType dbType() {
        return DbType.Clickhouse;
    }

    @Override
    public String tableSql(String tableName) {
        StringBuilder sql = new StringBuilder("""
                select *
                from system.tables
                where 1=1
                """);
        // 表名查询
        if (StringUtils.isNotBlank(tableName)) {
            sql.append("and name = '").append(tableName).append("' ");
        }
        sql.append("order by name asc");
        return sql.toString();
    }

    @Override
    public String tableName() {
        return "name";
    }

    @Override
    public String tableComment() {
        return "comment";
    }

    @Override
    public String tableFieldsSql() {
        return """
                select *
                from system.columns
                where table = '%s'
                """;
    }

    @Override
    public String fieldName() {
        return "name";
    }

    @Override
    public String fieldType() {
        return "type";
    }

    @Override
    public String fieldComment() {
        return "comment";
    }

    @Override
    public String fieldKey() {
        return "is_in_primary_key";
    }

    @Override
    public String fieldCharacterMaximumLength() {
        return null;
    }

}
