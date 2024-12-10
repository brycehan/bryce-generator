package com.brycehan.boot.generator.config.query;


import com.brycehan.boot.generator.config.DbType;
import cn.hutool.core.util.StrUtil;

/**
 * Oracle查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public class OracleDbQuery implements DbQuery {

    @Override
    public DbType dbType() {
        return DbType.Oracle;
    }

    @Override
    public String tableSql(String tableName) {
        StringBuilder sql = new StringBuilder();
        sql.append("""
                select dt.table_name, dtc.comments
                from user_tables dt, user_tab_comments dtc
                where dt.table_name = dtc.table_name
                """);
        // 表名查询
        if (StrUtil.isNotBlank(tableName)) {
            sql.append("and dt.table_name = '").append(tableName).append("' ");
        }
        sql.append("order by dt.table_name asc");
        return sql.toString();
    }

    @Override
    public String tableName() {
        return "table_name";
    }

    @Override
    public String tableComment() {
        return "comments";
    }

    @Override
    public String tableFieldsSql() {
        return """
                select a.column_name, a.data_type, b.comments,decode(c.position, '1', 'PRI') as key
                from all_tab_columns as a
                inner join all_col_comments b on a.table_name = b.table_name and a.column_name = b.column_name and b.owner = '#schema'
                left join all_constraints d on d.table_name = a.table_name and d.constraint_type = 'P' and d.owner = '#schema'
                left join all_cons_columns c on c.constraint_name = d.constraint_name and c.column_name = a.column_name and c.owner = '#schema'
                where a.owner = '#schema' and a.table_name = '%s'
                order by a.column_id
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
        return "comments";
    }

    @Override
    public String fieldKey() {
        return "key";
    }

    @Override
    public String fieldCharacterMaximumLength() {
        return null;
    }

}
