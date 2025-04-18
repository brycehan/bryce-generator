package ${packageName}.${moduleName}.mapper;

import ${packageName}.common.mybatis.mapper.BryceBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${packageName}.${moduleName}.entity.po.${entityName};

/**
* ${tableComment}Mapper接口
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${mapperName} extends BryceBaseMapper<${entityName}> {

    String columns = """
    <#list fieldList?chunk(5) as row>
        <#list row as field>${field.fieldName}<#sep>, </#sep></#list><#sep>,</#sep>
    </#list>
    """;

}
