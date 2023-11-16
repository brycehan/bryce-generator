package com.brycehan.boot.generator.config.query;


import com.brycehan.boot.generator.config.DbType;
import org.apache.commons.lang3.StringUtils;

/**
 * SQLServer查询
 *
 * @author Bryce Han
 * @since 2023/5/5
 */
public class SQLServerDbQuery implements DbQuery {

    @Override
    public DbType dbType() {
        return DbType.SQLServer;
    }

    @Override
    public String tableSql(String tableName) {
        StringBuilder sql = new StringBuilder("""
                select cast(so.name as varcher(500)) as table_name, cast(sep.value as varchar(500)) as table_comment
                from sysobjects so
                left join sys.extended_properties sep on sep.major_id = so.id and sep.minor_id = 0
                where (xtype = 'U' or xtype = 'V')
                """);
        // 表名查询
        if (StringUtils.isNotBlank(tableName)) {
            sql.append("and cast(so.name as varcher(500)) = '").append(tableName).append("' ");
        }
        sql.append("order by cast(so.name as varcher(500)) asc");
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
                select cast(a.name as varchar(500)) as table_name, cast(b.name as varchar(500)) as column_name,
                    cast(c.value as nvarchar(500)) as table_comment,cast(sys.types.name as varchar(500)) as data_type,
                    (
                        select case count(1) when 1 then 'PRI' else '' end
                        from syscolumns,sysobjects,sysindexes,sysindexkeys,systypes
                        where syscolumns.xusertype = systypes.xusertype
                            and syscolumns.id = object_id(a.name)
                            and sysobjects.xtype = 'PK'
                            and sysobjects.parent_obj = syscolumns.id
                            and sysindexes.id = syscolumns.id
                            and sysobjects.name = sysindexes.name
                            and sysindexkeys.id = syscolumns.id
                            and sysindexkeys.indid = sysindexes.indid
                            and syscolumns.colid = sysindexkeys.colid
                            and syscolumns.name = b.name
                    ) as 'column_key',
                    b.is_identity isIdentity
                from (
                        select name, object_id from sys.tables
                        union all
                        select name, object_id from sys.views
                    ) as a
                inner join sys.columns b on b.object_id = a.object_id
                left join sys.types on b.user_type_id = sys.types.user_type_id
                left join sys.extended_properties c on c.major_id = b.object_id and c.minor_id = b.column_id
                where a.name = '%s' and sys.types.name != 'sysname'
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
