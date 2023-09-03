package ${packageName}.${moduleName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${packageName}.${moduleName}.entity.${entityName};

/**
* ${tableComment}Mapper接口
*
* @author ${author}
* @since ${date}
*/
@Mapper
public interface ${mapperName} extends BaseMapper<${entityName}> {

}
