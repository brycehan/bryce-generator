package com.brycehan.boot.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.boot.generator.entity.po.FieldType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Set;

/**
 * 字段类型管理Mapper接口
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
public interface FieldTypeMapper extends BaseMapper<FieldType> {

    /**
     * 根据表ID获取包名
     *
     * @param tableId      表ID
     * @param baseClassId  基类ID
     * @param type         类型
     * @return 包名集合
     */
    @SelectProvider(type = SelectSqlProvider.class, method = "getPackageNameByTableId")
    Set<String> getPackageNameByTableId(Long tableId, Long baseClassId, String type);

    /**
     * 查询SQL提供者
     */
    class SelectSqlProvider {

        /**
         * 根据表ID获取包名
         *
         * @return SQL
         */
        @SuppressWarnings("all")
        public static String getPackageNameByTableId(Long tableId, Long baseClassId, String type) {
            return """
                    <script>
                        select distinct bgft.package_name
                        from brc_gen_field_type bgft
                        inner join brc_gen_table_field bgtf on bgft.attr_type = bgtf.attr_type
                        where bgtf.table_id = #{tableId}
                            <if test="type == 'vo'">
                                and (
                                    bgtf.primary_key = true
                                    or bgtf.form_item = true
                                    or bgtf.query_item = true
                                    or bgtf.grid_item = true
                                    )
                            </if>
                            <if test="type == 'entity' and baseClassId != null">
                                and bgtf.base_field = false
                            </if>
                    </script>
                    """;
        }
    }
}
