package ${packageName}.${moduleName}.entity.dto;

import com.brycehan.boot.common.entity.BaseDto;
<#if fieldList?filter(field -> field.characterMaximumLength gt 0)?size  gt 0>
import com.brycehan.boot.common.base.validator.SaveGroup;
import com.brycehan.boot.common.base.validator.UpdateGroup;
import org.hibernate.validator.constraints.Length;
</#if>
<#-- 字段列表 -->
<#list fieldList as field>
<#if field.attrName == "status">
import ${packageName}.common.enums.StatusType;
</#if>
</#list>
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
<#list importList as item>
import ${item!};
</#list>

/**
 * ${tableComment} Dto
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
    @Length(max = ${field.characterMaximumLength?c}, groups = {SaveGroup.class, UpdateGroup.class})
  </#if>
    <#if field.attrName == "status">
    private StatusType ${field.attrName};
    <#else>
    private ${field.attrType} ${field.attrName};
    </#if>

</#if>
</#list>
}
