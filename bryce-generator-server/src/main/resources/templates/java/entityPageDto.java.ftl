package ${packageName}.${moduleName}.dto;

import ${packageName}.common.base.entity.BasePageDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
<#list importList as item>
import ${item!};
</#list>

import java.io.Serial;

/**
 * ${tableComment}PageDto
 *
 * @author ${author}
 * @since ${date}
 */
@Schema(description = "${tableComment}PageDto")
@Data
@EqualsAndHashCode(callSuper = false)
public class ${entityName}PageDto extends BasePageDto {

    @Serial
    private static final long serialVersionUID = 1L;

<#list queryList as field>
<#if !field.baseField>
	<#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}
     */
	</#if>
    @Schema(description = "${field.fieldComment}")
  <#if field.characterMaximumLength gt 0>
    @Size(max = ${field.characterMaximumLength?c})
  </#if>
    private ${field.attrType} ${field.attrName};

</#if>
</#list>
}
