package com.brycehan.boot.generator.config.query;


import com.brycehan.boot.generator.config.DbType;
import org.apache.commons.lang3.StringUtils;

/**
 * MySQL查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public class MySQLDbQuery implements DbQuery {

    @Override
    public DbType dbType() {
        return DbType.MySQL;
    }

    @Override
    public String tableSql(String tableName) {
        StringBuilder sql = new StringBuilder("""
                select table_name, table_comment
                from information_schema.tables
                where table_schema = (select database())
                """);
        // 表名查询
        if (StringUtils.isNotBlank(tableName)) {
            sql.append("and table_name = '").append(tableName).append("' ");
        }
        sql.append("order by table_name asc");
        return sql.toString();
    }

    @Override
    public String tableName() {
        return "table_name";
    }

    @Override
    public String tableComment() {
        return "table_comment";
    }

    @Override
    public String tableFieldsSql() {
        return """
                select column_name, ordinal_position, data_type, character_maximum_length, column_default,
                is_nullable, column_comment, column_key, numeric_precision, numeric_scale
                from information_schema.columns
                where table_name = '%s' and table_schema = (select database())
                order by ordinal_position
                """;
    }

    @Override
    public String fieldName() {
        return "column_name";
    }

    @Override
    public String fieldType() {
        return "data_type";
    }

    @Override
    public String fieldComment() {
        return "column_comment";
    }

    @Override
    public String fieldKey() {
        return "column_key";
    }

    @Override
    public String fieldCharacterMaximumLength() {
        return "character_maximum_length";
    }

}
