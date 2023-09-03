<template>
  <el-dialog
    v-model="visible"
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
  >
    <el-form
      ref="dataFormRef"
      :model="dataForm"
      :rules="rules"
      label-width="100px"
      @keyup.enter="handleSubmit()"
    >
<#if formLayout == 2>
  <#list formList?chunk(2) as row>
      <el-row>
    <#list row as field>
        <el-col :span="12">
        <#if field.formItemType == 'text'>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-input v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}" />
          </el-form-item>
        <#elseif field.formItemType == 'textarea'>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-input type="textarea" v-model="dataForm.${field.attrName}" />
          </el-form-item>
        <#elseif field.formItemType == 'editor'>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-input type="textarea" v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}" />
          </el-form-item>
        <#elseif field.formItemType == 'select'>
          <#if field.formDict??>
            <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
              <dict-select v-model="dataForm.${field.attrName}" dict-type="${field.formDict}" placeholder="${field.fieldComment!}" />
            </el-form-item>
          <#else>
            <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
              <el-select v-model="dataForm.${field.attrName}" placeholder="请选择">
                <el-option label="请选择" value="0" />
              </el-select>
            </el-form-item>
          </#if>
        <#elseif field.formItemType == 'radio'>
          <#if field.formDict??>
            <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
              <dict-radio-group v-model="dataForm.${field.attrName}" dict-type="${field.formDict}" />
            </el-form-item>
          <#else>
            <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
              <el-radio-group v-model="dataForm.${field.attrName}">
                <el-radio :label="0">启用</el-radio>
                <el-radio :label="1">禁用</el-radio>
              </el-radio-group>
            </el-form-item>
          </#if>
        <#elseif field.formItemType == 'checkbox'>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-checkbox-group v-model="dataForm.${field.attrName}">
              <el-checkbox :label="启用" name="type"/>
              <el-checkbox :label="禁用" name="type"/>
            </el-checkbox-group>
          </el-form-item>
        <#elseif field.formItemType == 'date'>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-date-picker type="date" v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}"/>
          </el-form-item>
        <#elseif field.formItemType == 'datetime'>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-date-picker type="datetime" v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}"/>
          </el-form-item>
        <#else>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-input v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}" />
          </el-form-item>
        </#if>
        </el-col>
    </#list>
      </el-row>
  </#list>
</#if>
<#if formLayout == 1>
<#list formList as field>
    <#if field.formItemType == 'text'>
      <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
        <el-input v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}" />
      </el-form-item>
    <#elseif field.formItemType == 'textarea'>
      <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
        <el-input type="textarea" v-model="dataForm.${field.attrName}" />
      </el-form-item>
    <#elseif field.formItemType == 'editor'>
        <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
          <el-input type="textarea" v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}" />
        </el-form-item>
    <#elseif field.formItemType == 'select'>
      <#if field.formDict??>
        <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
          <dict-select v-model="dataForm.${field.attrName}" dict-type="${field.formDict}" placeholder="${field.fieldComment!}" />
        </el-form-item>
      <#else>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-select v-model="dataForm.${field.attrName}" placeholder="请选择">
              <el-option label="请选择" value="0" />
            </el-select>
          </el-form-item>
      </#if>
    <#elseif field.formItemType == 'radio'>
        <#if field.formDict??>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <dict-radio-group v-model="dataForm.${field.attrName}" dict-type="${field.formDict}" />
          </el-form-item>
        <#else>
          <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
            <el-radio-group v-model="dataForm.${field.attrName}">
              <el-radio :label="0">启用</el-radio>
              <el-radio :label="1">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </#if>
    <#elseif field.formItemType == 'checkbox'>
        <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
          <el-checkbox-group v-model="dataForm.${field.attrName}">
            <el-checkbox :label="启用" name="type"/>
            <el-checkbox :label="禁用" name="type"/>
          </el-checkbox-group>
        </el-form-item>
    <#elseif field.formItemType == 'date'>
        <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
          <el-date-picker type="date" v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}"/>
        </el-form-item>
    <#elseif field.formItemType == 'datetime'>
        <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
          <el-date-picker type="datetime" v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}"/>
        </el-form-item>
    <#else>
        <el-form-item label="${field.fieldComment!}" prop="${field.attrName}">
          <el-input v-model="dataForm.${field.attrName}" placeholder="${field.fieldComment!}" />
        </el-form-item>
    </#if>
</#list>
</#if>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit()">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getById, addOrUpdate } from '@/api/${moduleName}/${functionName}'

const emit = defineEmits(['refreshPage'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
    <#list fieldList as field>
    ${field.attrName}: ''<#sep>,</#sep>
    </#list>
})

const rules = reactive({
  <#list formList as field>
    <#if field.formRequired>
    ${field.attrName}: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
    </#if>
  </#list>
})

const init = (id?: string) => {
    visible.value = true
    dataForm.id = ''

    // 重置表单数据
    if (dataFormRef.value) {
        dataFormRef.value.resetFields()
    }

    // id 存在则为修改
    if (id) {
        get${FunctionName}(id)
    }
}

const get${FunctionName} = (id: string) => {
    getById(id).then((res) => {
        Object.assign(dataForm, res.data)
    })
}

/** 表单提交 */
const handleSubmit = () => {
    dataFormRef.value.validate((valid: boolean) => {
        if (!valid) {
            return false
        }

        addOrUpdate(dataForm).then(() => {
            ElMessage.success({
                message: '操作成功',
                duration: 500,
                onClose: () => {
                    visible.value = false
                    emit('refreshPage')
                }
            })
        })
    })
}

defineExpose({
    init
})
</script>
