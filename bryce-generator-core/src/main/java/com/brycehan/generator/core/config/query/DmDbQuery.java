package com.brycehan.generator.core.config.query;


import com.brycehan.generator.core.config.DbType;
import org.apache.commons.lang3.StringUtils;

/**
 * 达梦查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public class DmDbQuery implements DbQuery {

    @Override
    public DbType dbType() {
        return DbType.DM;
    }

    @Override
    public String tableSql(String tableName) {
        StringBuilder sql = new StringBuilder("""
                select t.*
                from (
                    select distinct t1.table_name as table_name, t2.comments as table_comment
                    from user_tab_columns t1
                    inner join user_tab_comments t2 on t1.tableL_name = t2.table_name
                ) as t
                where 1=1
                """);
        // 表名查询
        if (StringUtils.isNotBlank(tableName)) {
            sql.append("and t.table_name = '").append(tableName).append("' ");
        }
        sql.append("order by t.table_name asc");
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
                select t2.column_name, t1.comments as column_comment,
                    case
                        when t2.data_type = 'number' then (
                            case
                                when t2.data_precision is null then t2.data_type
                                when nvl(t2.data_scale, 0) > 0 then t2.data_type || '(' || t2.data_precision || ',' || t2.data_scale || ')'
                                else t2.data_type || '(' || t2.data_precision || ')'
                            end
                        )
                        else t2.data_type
                    end as data_type,
                    case
                        when constraint_type = 'P' then 'PRI'
                    end as column_key
                from user_col_comments t1, user_tab_column t2,
                    (
                        select t4.table_name, t4.column_name, t5.constraint_type
                        from user_cons_columns t4, user_constraints t5
                        where t4.constraint_name = t5.constraint_name and t5.constraint_type = 'P'
                    ) as t3
                where t1.table_name = t2.table_name
                    and t1.column_name = t2.column_name
                    and t1.table_name = t3.table_name(+)
                    and t1.column_name = t3.column_name(+)
                    and t1.table_name = '%s'
                order by t2.table_name, t2.column_id
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
