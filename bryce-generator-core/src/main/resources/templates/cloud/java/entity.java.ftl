package ${packageName}.${moduleName}.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
<#if baseClass??>
import lombok.EqualsAndHashCode;
</#if>
<#list importList as item>
import ${item!};
</#list>
<#-- 字段列表 -->
<#list fieldList as field>
<#if field.attrName == "status">
import ${packageName}.common.core.enums.StatusType;
</#if>
</#list>
<#if baseClass??>
import ${packageName}.common.core.entity.${baseClass.code};
<#else>
import java.io.Serializable;
import java.io.Serial;
</#if>

/**
 * ${tableComment} entity
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
	<#if field.attrName == "status">
    private StatusType ${field.attrName};
	<#else>
    private ${field.attrType} ${field.attrName};
  </#if>

</#if>
</#list>
}
