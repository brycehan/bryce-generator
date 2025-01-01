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
      label-width="120"
      @keyup.enter="handleSubmit()"
    >
      <el-form-item label="连接名" prop="connName">
        <el-input v-model="dataForm.connName" placeholder="连接名" />
      </el-form-item>
      <el-form-item label="数据库类型" prop="dbType">
        <el-select v-model="dataForm.dbType" placeholder="数据库类型" clearable style="width: 100%">
          <el-option value="MySQL" label="MySQL" />
          <el-option value="PostgreSQL" label="PostgreSQL" />
          <el-option value="Oracle" label="Oracle" />
          <el-option value="SQLServer" label="SQLServer" />
          <el-option value="DM" label="达梦8" />
          <el-option value="Clickhouse" label="Clickhouse" />
        </el-select>
      </el-form-item>
      <el-form-item label="数据库URL" prop="connUrl">
        <el-input v-model="dataForm.connUrl" placeholder="数据库URL" />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="dataForm.username" placeholder="用户名" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="dataForm.password" type="password" placeholder="密码" />
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
import { getById, saveOrUpdate } from '@/api/datasource'
import { ElMessage } from 'element-plus'

const emit = defineEmits(['refreshPage'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
  id: '',
  dbType: '',
  connName: '',
  connUrl: '',
  username: '',
  password: ''
})

const rules = reactive({
  dbType: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  connName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  connUrl: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  username: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  password: [{ required: true, message: '必填项不能为空', trigger: 'blur' }]
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
    getDatasource(id)
  }
}

const getDatasource = (id: string) => {
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
