package ${packageName}.${moduleName}.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
<#if baseClass??>
import lombok.EqualsAndHashCode;
</#if>
<#list importList as item>
import ${item!};
</#list>
<#if baseClass?? && packageName == "com.brycehan.cloud">
import ${packageName}.common.core.base.entity.${baseClass.code};
<#elseif baseClass??>
import ${baseClass.packageName}.${baseClass.code};
<#else>
import java.io.Serializable;
import java.io.Serial;
</#if>

/**
 * ${tableComment}entity
 *
 * @author ${author}
 * @since ${date}
 */
@Data
<#if baseClass??>
@EqualsAndHashCode(callSuper = true)
</#if>
@TableName("${tableName}")
public class ${entityName} <#if baseClass??>extends ${baseClass.code}<#else>implements Serializable</#if> {
<#if !baseClass??>

    @Serial
    private static final long serialVersionUID = 1L;
</#if>

<#list fieldList as field>
<#if !field.baseField>
	<#if field.fieldComment!?length gt 0>
    /**
     * ${field.fieldComment}
     */
	</#if>
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
