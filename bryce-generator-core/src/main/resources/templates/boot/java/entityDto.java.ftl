package ${packageName}.${moduleName}.entity.dto;

import com.brycehan.cloud.common.core.base.entity.BaseDto;
<#if fieldList?filter(field -> field.characterMaximumLength gt 0)?size  gt 0>
import com.brycehan.boot.common.validator.SaveGroup;
import com.brycehan.boot.common.validator.UpdateGroup;
import jakarta.validation.constraints.Size;
</#if>
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list importList as item>
import ${item!};
</#list>

/**
 * ${tableComment}Dto
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "${tableComment}Dto")
public class ${entityName}Dto extends BaseDto {

<#if baseClass??>
    /**
     * ID
     */
    @Schema(description = "ID")
    @Null(groups = SaveGroup.class)
    @NotNull(groups = UpdateGroup.class)
    private Long id;

</#if>
<#list fieldList as field>
<#if !field.baseField>
	<#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}
     */
	</#if>
    @Schema(description = "${field.fieldComment}")
  <#if field.primaryKey>
    @Null(groups = SaveGroup.class)
    @NotNull(groups = UpdateGroup.class)
  </#if>
  <#if field.characterMaximumLength gt 0>
    @Size(max = ${field.characterMaximumLength?c}, groups = {SaveGroup.class, UpdateGroup.class})
  </#if>
    private ${field.attrType} ${field.attrName};

</#if>
</#list>
}
