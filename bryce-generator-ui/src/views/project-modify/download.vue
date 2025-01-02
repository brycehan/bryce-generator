<template>
  <el-dialog
      v-model="visible"
      title="源码下载"
      :close-on-click-modal="false"
  >
    <el-form
        ref="dataFormRef"
        :model="dataForm"
        :rules="rules"
        label-width="100"
        @keyup.enter="handleSubmit()"
    >
      <el-row>
        <el-form-item label="项目名" prop="projectName" style="width: 100%">
          <el-input v-model="dataForm.projectName" disabled placeholder="项目名" />
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="项目标识" prop="projectCode">
            <el-input v-model="dataForm.projectCode" disabled placeholder="项目标识" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="标识缩写" prop="projectCode">
            <el-input v-model="dataForm.projectCodeAbbreviate" disabled placeholder="标识缩写" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="项目包名" prop="projectPackage">
            <el-input v-model="dataForm.projectPackage" disabled placeholder="项目包名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="项目路径" prop="projectPath">
            <el-input v-model="dataForm.projectPath" disabled placeholder="项目路径" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-divider>变更后的信息</el-divider>
      <el-row>
        <el-col :span="12">
          <el-form-item label="项目名" prop="modifyProjectName">
            <el-input v-model="dataForm.modifyProjectName" placeholder="项目名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="项目标识" prop="modifyProjectCode">
            <el-input v-model="dataForm.modifyProjectCode" placeholder="项目标识" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="标识缩写" prop="modifyProjectCodeAbbreviate">
            <el-input v-model="dataForm.modifyProjectCodeAbbreviate" placeholder="标识缩写" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="项目包名" prop="modifyProjectPackage">
            <el-input v-model="dataForm.modifyProjectPackage" placeholder="项目包名" />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="排除文件" prop="exclusions">
            <el-input v-model="dataForm.exclusions" placeholder="排除文件" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="变更文件" prop="modifySuffix">
            <el-input v-model="dataForm.modifySuffix" placeholder="变更文件" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit()">下载</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {reactive, ref} from 'vue'
import {getById, saveOrUpdate, sourceDownload} from '@/api/projectModify'

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
  id: '',
  projectName: '',
  projectPath: '',
  projectCode: '',
  projectCodeAbbreviate: '',
  projectPackage: '',
  modifyProjectName: '',
  modifyProjectCode: '',
  modifyProjectCodeAbbreviate: '',
  modifyProjectPackage: '',
  exclusions: 1,
  modifySuffix: '',
})

const rules = reactive({
  modifyProjectName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  modifyProjectCode: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  modifyProjectCodeAbbreviate: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  modifyProjectPackage: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  exclusions: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  modifySuffix: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
})

const init = (id: string) => {
  visible.value = true
  dataForm.id = ''

  // 重置表单数据
  if (dataFormRef.value) {
    dataFormRef.value.resetFields()
  }

  getProject(id)
}

const getProject = (id: string) => {
  getById(id).then((res) => {
    Object.assign(dataForm, res.data)
  })
}

/** 保存 */
const handleSubmit = () => {
  dataFormRef.value.validate(async (valid: boolean) => {
    if (!valid) {
      return false
    }

    // 先保存
    await saveOrUpdate(dataForm)

    // 源码下载
    sourceDownload(dataForm.id)
    visible.value = false
  })
}

defineExpose({
  init
})
</script>
