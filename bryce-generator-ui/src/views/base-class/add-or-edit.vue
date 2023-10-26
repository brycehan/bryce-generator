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
      label-width="120px"
      @keyup.enter="handleSubmit()"
    >
      <el-form-item label="基类编码" prop="code">
        <el-input v-model="dataForm.code" placeholder="基类编码" />
      </el-form-item>
      <el-form-item label="基类包名" prop="packageName">
        <el-input v-model="dataForm.packageName" placeholder="基类包名" />
      </el-form-item>
      <el-form-item label="基类字段" prop="fields">
        <el-input v-model="dataForm.fields" placeholder="基类字段（多个字段，用英文逗号分隔）" />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="备注" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit()">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { getById, saveOrUpdate } from '@/api/baseClass'
import { ElMessage } from 'element-plus'

const emit = defineEmits(['refreshPage'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
  id: '',
  code: '',
  packageName: '',
  fields: '',
  remark: '',
})

const rules = reactive({
  code: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  packageName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  fields: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
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
    getBaseClass(id)
  }
}

const getBaseClass = (id: string) => {
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

    saveOrUpdate(dataForm).then(() => {
      visible.value = false
      emit('refreshPage')
      ElMessage.success('操作成功')
    })
  })
}

defineExpose({
  init
})
</script>
