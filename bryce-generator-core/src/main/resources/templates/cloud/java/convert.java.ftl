package ${packageName}.${moduleName}.entity.convert;

import ${packageName}.${moduleName}.entity.dto.${entityName}Dto;
import ${packageName}.${moduleName}.entity.po.${entityName};
import ${packageName}.${moduleName}.entity.vo.${entityName}Vo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * ${tableComment}转换器
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${convertName} {

    ${convertName} INSTANCE = Mappers.getMapper(${convertName}.class);

    ${entityName} convert(${entityName}Dto ${entityParam}Dto);

    ${entityName}Vo convert(${entityName} ${entityParam});

    List<${entityName}Vo> convert(List<${entityName}> ${entityParam}List);

}