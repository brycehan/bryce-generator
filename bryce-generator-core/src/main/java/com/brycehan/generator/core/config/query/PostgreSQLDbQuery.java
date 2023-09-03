package com.brycehan.generator.core.config.query;


import com.brycehan.generator.core.config.DbType;
import org.apache.commons.lang3.StringUtils;

/**
 * Postgresql查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public class PostgreSQLDbQuery implements DbQuery {

    @Override
    public DbType dbType() {
        return DbType.PostgreSQL;
    }

    @Override
    public String tableSql(String tableName) {

        String sql = """
                select t1.tablename as table_name, obj_description(relfilenode, 'pg_class') as table_comment
                from pg_tables t1, pg_class t2
                where t1.tablename not like 'pg%'
                    and t1.tablename not like 'sql_%'
                    and t1.tablename = t2.relname
                """;
        // 表名查询
        if (StringUtils.isNotBlank(tableName)) {
            sql = sql.concat("and t1.tablename = '").concat(tableName).concat("' ");
        }

        return sql;
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
                select t2.attname as column_name, pg_type.typname as data_type,
                    col_description(t2.attrelid,t2.attnum) as column_comment,
                    (case t3.contype when 'p' then 'PRI' else '' end) as column_key
                from pg_class as t1, pg_attribute as t2
                inner join pg_type on pg_type.oid=t2.atttypid
                left join pg_constraint t3 on t2.attnum = t3.conKey[1] and t2.attrelid = t3.conrelid
                where t1.relname = '%s' and t2.attrelid = t1.oid and t2.attnum > 0
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
        return null;
    }

}
