package ${packageName}.${moduleName}.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list importList as item>
import ${item!};
</#list>

import java.io.Serializable;
import java.io.Serial;

/**
 * ${tableComment}Vo
 *
 * @author ${author}
 * @since ${date}
 */
@Schema(description = "${tableComment}Vo")
@Data
<#if baseClass??>
@EqualsAndHashCode(callSuper=false)
</#if>
public class ${entityName}Vo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

<#list fieldList as field>
<#if !field.baseField>
	<#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}
     */
	</#if>
    @Schema(description = "${field.fieldComment}")
    private ${field.attrType} ${field.attrName};

</#if>
</#list>
}
