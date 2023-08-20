package ${packageName}.${moduleName}.entity;

import com.baomidou.mybatisplus.annotation.*;
import ${packageName}.common.base.entity.BasePo;
import ${packageName}.common.validator.group.AddGroup;
import ${packageName}.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
<#if baseClass??>import lombok.EqualsAndHashCode;</#if>
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
<#list importList as item>
	import ${item!};
</#list>
<#if baseClass??>
	import ${baseClass.packageName}.${baseClass.code}
</#if>
import java.io.Serial;
import java.time.LocalDateTime;

/**
* ${tableComment}
*
* @author ${author}
* @since ${date}
*/
<#if baseClass??>@EqualsAndHashCode(callSuper=false)</#if>
@Data
@TableName("${tableName}")
@Schema(description = "${entityName}实体")
public class ${entityName} <#if baseClass??> extends ${baseClass.code}<#else> implements Serializable</#if> {

@Serial
private static final long serialVersionUID = 1L;

<#list fieldList as field>
    <#if !field.baseField??>
        <#if field.fieldComment!?length gt 0>
					/**
					* ${field.fieldComment}
					*/
        </#if>
			@Schema(description = "${field.fieldComment}")
        <#if field.autoFill == "INSERT">
					@TableField(fill = FieldFill.INSERT)
        </#if>
        <#if field.autoFill == "INSERT_UPDATE">
					@TableField(fill = FieldFill.INSERT_UPDATE)
        </#if>
        <#if field.autoFill == "UPDATE">
					@TableField(fill = FieldFill.UPDATE)
        </#if>
        <#if field.primaryKey>
					@TableId
        </#if>
			private ${field.attrType} ${field.attrName};
    </#if>
</#list>

}
