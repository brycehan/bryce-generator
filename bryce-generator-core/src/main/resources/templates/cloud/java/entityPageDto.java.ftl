package ${packageName}.${moduleName}.entity.dto;

import ${packageName}.common.core.entity.BasePageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
<#-- 区间查询条件 -->
<#list queryList as field>
<#if field.attrType == "LocalDateTime" || field.attrType == "LocalDate" || field.attrType == "LocalTime">
import java.time.${field.attrType};
<#elseif field.attrType == "BigDecimal">
import java.math.BigDecimal;
<#elseif field.attrName == "status">
import ${packageName}.common.core.enums.StatusType;
</#if>
</#list>

/**
 * ${tableComment}PageDto
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Schema(description = "${tableComment}PageDto")
@EqualsAndHashCode(callSuper = true)
public class ${entityName}PageDto extends BasePageDto {

<#list queryList as field>
<#-- 区间查询条件 -->
<#if field.queryType == "between">
  <#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}开始
     */
  </#if>
    @Schema(description = "${field.fieldComment}开始")
    private ${field.attrType} ${field.attrName}Start;

  <#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}结束
     */
  </#if>
    @Schema(description = "${field.fieldComment}结束")
    private ${field.attrType} ${field.attrName}End;

<#-- 一般查询条件 -->
<#else>
	<#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}
     */
	</#if>
    @Schema(description = "${field.fieldComment}")
  <#if field.characterMaximumLength gt 0>
    @Length(max = ${field.characterMaximumLength?c})
  </#if>
    <#if field.attrName == "status">
    private StatusType ${field.attrName};
    <#else>
    private ${field.attrType} ${field.attrName};
    </#if>

</#if>
</#list>
}
