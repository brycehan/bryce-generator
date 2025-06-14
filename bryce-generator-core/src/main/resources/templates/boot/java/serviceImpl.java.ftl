package ${packageName}.${moduleName}.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${packageName}.common.entity.PageResult;
import ${packageName}.framework.mybatis.service.impl.BaseServiceImpl;
import ${packageName}.common.util.excel.ExcelUtils;
import ${packageName}.common.base.IdGenerator;
import ${packageName}.${moduleName}.entity.convert.${convertName};
import ${packageName}.${moduleName}.entity.dto.${entityName}Dto;
import ${packageName}.${moduleName}.entity.dto.${entityPageDtoName};
import ${packageName}.${moduleName}.entity.po.${entityName};
import ${packageName}.${moduleName}.entity.vo.${entityName}Vo;
import ${packageName}.${moduleName}.service.${serviceName};
import ${packageName}.${moduleName}.mapper.${mapperName};
<#if queryList?filter(f -> f.attrType == "String")?size gt 0>
import org.apache.commons.lang3.StringUtils;
</#if>
<#if queryList?filter(f -> f.attrType != "String")?size gt 0>
import java.util.Objects;
</#if>
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import lombok.extern.slf4j.Slf4j;


/**
 * ${tableComment}服务实现
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ${serviceImplName} extends BaseServiceImpl<${mapperName}, ${entityName}> implements ${serviceName} {

    /**
     * 添加${tableComment}
     *
     * @param ${entityParam}Dto ${tableComment}Dto
     */
    public void save(${entityName}Dto ${entityParam}Dto) {
        ${entityName} ${entityParam} = ${convertName}.INSTANCE.convert(${entityParam}Dto);
        ${entityParam}.setId(IdGenerator.nextId());
        baseMapper.insert(${entityParam});
    }

    /**
     * 更新${tableComment}
     *
     * @param ${entityParam}Dto ${tableComment}Dto
     */
    public void update(${entityName}Dto ${entityParam}Dto) {
        ${entityName} ${entityParam} = ${convertName}.INSTANCE.convert(${entityParam}Dto);
        baseMapper.updateById(${entityParam});
    }

    @Override
    public PageResult<${entityName}Vo> page(${entityPageDtoName} ${entityParam}PageDto) {
        IPage<${entityName}> page = baseMapper.selectPage(${entityParam}PageDto.toPage(), getWrapper(${entityParam}PageDto));
        return PageResult.of(${convertName}.INSTANCE.convert(page.getRecords()), page.getTotal());
    }

    /**
     * 封装查询条件
     *
     * @param ${entityParam}PageDto ${tableComment}分页dto
     * @return 查询条件Wrapper
     */
    private LambdaQueryWrapper<${entityName}> getWrapper(${entityPageDtoName} ${entityParam}PageDto){
        LambdaQueryWrapper<${entityName}> wrapper = new LambdaQueryWrapper<>();
        <#list queryList?sort_by("queryType") as field>
          <#if field.queryType == '=' && field.attrType == 'String'>
        wrapper.eq(StringUtils.isNotEmpty(${entityParam}PageDto.get${field.attrName?cap_first}()), ${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}());
          <#elseif field.queryType == '=' && field.attrType != 'String'>
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
          <#elseif field.queryType == 'between'>

        if(${entityParam}PageDto.get${field.attrName?cap_first}Start() != null && ${entityParam}PageDto.get${field.attrName?cap_first}End() != null) {
            wrapper.between(${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}Start(), ${entityParam}PageDto.get${field.attrName?cap_first}End());
        } else if(${entityParam}PageDto.get${field.attrName?cap_first}Start() != null) {
            wrapper.ge(${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}Start());
        }else if(${entityParam}PageDto.get${field.attrName?cap_first}End() != null) {
            wrapper.ge(${entityName}::get${field.attrName?cap_first}, ${entityParam}PageDto.get${field.attrName?cap_first}End());
        }

          </#if>
        </#list>
        return wrapper;
    }

    @Override
    public void export(${entityPageDtoName} ${entityParam}PageDto) {
        List<${entityName}> ${entityParam}List = baseMapper.selectList(getWrapper(${entityParam}PageDto));
        List<${entityName}Vo> ${entityParam}VoList = ${convertName}.INSTANCE.convert(${entityParam}List);
        String today = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        ExcelUtils.export(${entityName}Vo.class, "${tableComment}_".concat(today), "${tableComment}", ${entityParam}VoList);
    }

}
