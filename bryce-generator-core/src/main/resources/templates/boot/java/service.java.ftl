package ${packageName}.${moduleName}.service;

import ${packageName}.framework.mybatis.service.BaseService;
import ${packageName}.common.entity.PageResult;
import ${packageName}.common.base.IdGenerator;
import ${packageName}.${moduleName}.entity.convert.${entityName}Convert;
import ${packageName}.${moduleName}.entity.dto.${entityName}Dto;
import ${packageName}.${moduleName}.entity.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.po.${entityName};
import ${packageName}.${moduleName}.entity.vo.${entityName}Vo;

/**
 * ${tableComment}服务
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${serviceName} extends BaseService<${entityName}> {

    /**
     * 添加${tableComment}
     *
     * @param ${entityParam}Dto ${tableComment}Dto
     */
    default void save(${entityName}Dto ${entityParam}Dto) {
        ${entityName} ${entityParam} = ${convertName}.INSTANCE.convert(${entityParam}Dto);
        ${entityParam}.setId(IdGenerator.nextId());
        this.getBaseMapper().insert(${entityParam});
    }

    /**
     * 更新${tableComment}
     *
     * @param ${entityParam}Dto ${tableComment}Dto
     */
    default void update(${entityName}Dto ${entityParam}Dto) {
        ${entityName} ${entityParam} = ${convertName}.INSTANCE.convert(${entityParam}Dto);
        this.getBaseMapper().updateById(${entityParam});
    }

    /**
     * ${tableComment}分页查询
     *
     * @param ${entityParam}PageDto 查询条件
     * @return 分页信息
     */
    PageResult<${entityName}Vo> page(${entityPageDtoName} ${entityParam}PageDto);

    /**
     * ${tableComment}导出数据
     *
     * @param ${entityParam}PageDto ${tableComment}查询条件
     */
    void export(${entityPageDtoName} ${entityParam}PageDto);

}
