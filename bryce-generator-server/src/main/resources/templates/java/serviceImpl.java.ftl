package ${packageName}.${moduleName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${packageName}.common.base.entity.PageResult;
import ${packageName}.common.base.http.HttpResponseStatusEnum;
import ${packageName}.common.exception.BusinessException;
import ${packageName}.framework.mybatis.service.impl.BaseServiceImpl;
import ${packageName}.common.util.ExcelUtils;
import ${packageName}.${moduleName}.convert.${convertName};
import ${packageName}.${moduleName}.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.${entityName};
import ${packageName}.${moduleName}.vo.${entityName}Vo;
import ${packageName}.${moduleName}.service.${serviceName};
import ${packageName}.${moduleName}.mapper.${mapperName};
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * ${tableComment}服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@RequiredArgsConstructor
public class ${serviceImplName} extends BaseServiceImpl<${mapperName}, ${entityName}> implements ${serviceName} {

    @Override
    public PageResult<${entityName}Vo> page(${entityPageDtoName} ${entityParam}PageDto) {

        IPage<${entityName}> page = this.baseMapper.selectPage(getPage(${entityParam}PageDto), getWrapper(${entityParam}PageDto));

        return new PageResult<>(page.getTotal(), ${convertName}.INSTANCE.convert(page.getRecords()));
    }

    /**
     * 封装查询条件
     *
     * @param ${entityParam}PageDto ${tableComment}分页dto
     * @return 查询条件Wrapper
     */
    private Wrapper<${entityName}> getWrapper(${entityPageDtoName} ${entityParam}PageDto){
        LambdaQueryWrapper<${entityName}> wrapper = new LambdaQueryWrapper<>();
        <#list queryList as field>
        	<#if field.queryFormType == 'date' || field.queryFormType == 'datetime'>
        wrapper.between(ArrayUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ArrayUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()) ? ${entityParam}PageDto.get${field.attrName?cap_first}()[0] : null, ArrayUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()) ? ${entityParam}PageDto.get${field.attrName?cap_first}()[1] : null);
          <#elseif field.queryType == '='>
        wrapper.eq(Objects.nonNull(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == '!='>
        wrapper.ne(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == '>'>
        wrapper.gt(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == '>='>
        wrapper.ge(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == '<'>
        wrapper.lt(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == '<='>
        wrapper.le(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == 'like'>
        wrapper.like(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == 'left like'>
        wrapper.likeLeft(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == 'right like'>
        wrapper.likeRight(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
        	</#if>
        </#list>
        return wrapper;
    }

    @Override
    public void export(${entityPageDtoName} ${entityParam}PageDto) {
        List<${entityName}> ${entityParam}List = this.baseMapper.selectList(getWrapper(${entityParam}PageDto));
        List<${entityName}Vo> ${entityParam}VoList = ${convertName}.INSTANCE.convert(${entityParam}List);
        ExcelUtils.export(${entityName}Vo.class, "${tableComment}", "${tableComment}", ${entityParam}VoList);
    }

}
