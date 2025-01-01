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
      label-width="100"
      @keyup.enter="handleSubmit()"
    >
      <el-form-item label="项目名" prop="projectName">
        <el-input v-model="dataForm.projectName" placeholder="项目名" />
      </el-form-item>

      <el-form-item label="项目标识" prop="projectCode">
        <el-input v-model="dataForm.projectCode" placeholder="项目标识" />
      </el-form-item>
      <el-form-item label="项目包名" prop="projectPackage">
        <el-input v-model="dataForm.projectPackage" placeholder="项目包名" />
      </el-form-item>
      <el-form-item label="项目路径" prop="projectPath">
        <el-input v-model="dataForm.projectPath" placeholder="项目路径" />
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
import { getById, saveOrUpdate } from '@/api/projectModify'
import { ElMessage } from 'element-plus'

const emit = defineEmits(['refreshPage'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
  id: '',
  projectName: '',
  projectCode: '',
  projectPackage: '',
  projectPath: '',
})

const rules = reactive({
  projectName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  projectCode: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  projectPackage: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  projectPath: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
})

const init = (id?: string) => {
  visible.value = true
  // dataForm.id = null

  // 重置表单数据
  if (dataFormRef.value) {
    dataFormRef.value.resetFields()
  }

  // id 存在则为修改
  if (id) {
    getProjectModify(id)
  }
}

const getProjectModify = (id: string) => {
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
