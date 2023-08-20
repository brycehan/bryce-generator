package ${packageName}.${moduleName}.mapper;

import ${packageName}.common.base.mapper.BryceBaseMapper;
import ${packageName}.${moduleName}.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.${entityName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* ${tableComment}Mapper接口
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface SysPostMapper extends BryceBaseMapper<${entityName}> {

}
