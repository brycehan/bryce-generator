package ${packageName}.${moduleName}.mapper;

import ${packageName}.framework.mybatis.BryceBaseMapper;
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

}
