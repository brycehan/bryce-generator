<template>
  <el-dialog
    v-model="visible"
    title="生成代码"
    :close-on-click-modal="false"
    draggable
  >
    <el-form
      ref="dataFormRef"
      :model="dataForm"
      :rules="rules"
      label-width="120"
      @keyup.enter="handleSubmit()"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="表名" prop="tableName">
            <el-input v-model="dataForm.tableName" disabled placeholder="表名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="说明" prop="tableComment">
            <el-input v-model="dataForm.tableComment" placeholder="说明" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="类名" prop="className">
            <el-input v-model="dataForm.className" placeholder="类名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="继承" prop="baseClassId">
            <el-select v-model="dataForm.baseClassId" placeholder="继承" style="width: 100%" clearable>
              <el-option v-for="item in baseClassList" :key="item.id" :label="item.code" :value="item.id"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="模块名" prop="moduleName">
            <el-input v-model="dataForm.moduleName" placeholder="模块名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="功能名" prop="functionName">
            <el-input v-model="dataForm.functionName" placeholder="功能名" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="项目包名" prop="packageName">
            <el-input v-model="dataForm.packageName" placeholder="项目包名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="版本号" prop="version">
            <el-input v-model="dataForm.version" placeholder="版本号" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="作者" prop="author">
            <el-input v-model="dataForm.author" placeholder="作者" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="作者邮箱" prop="email">
            <el-input v-model="dataForm.email" placeholder="作者邮箱" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="生成方式" prop="generatorType">
            <el-radio-group v-model="dataForm.generatorType" >
              <el-radio :label="0">zip压缩包</el-radio>
              <el-radio :label="1">自定义路径</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="表单布局" prop="formLayout">
            <el-radio-group v-model="dataForm.formLayout" >
              <el-radio :label="1">一列</el-radio>
              <el-radio :label="2">两列</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item v-if="dataForm.generatorType === 1" label="后端生成路径" prop="backendPath">
        <el-input v-model="dataForm.backendPath" placeholder="后端生成路径" />
      </el-form-item>
      <el-form-item v-if="dataForm.generatorType === 1" label="前端生成路径" prop="frontendPath">
        <el-input v-model="dataForm.frontendPath" placeholder="前端生成路径" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit()">保存</el-button>
      <el-button type="success" @click="handleGen()">生成代码</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { list as baseClassListApi } from '@/api/baseClass'
import { downloadZip, custom } from '@/api/generator'
import { getById as tableApi, update as updateTable } from '@/api/table'
import { ElMessage } from 'element-plus'

const emit = defineEmits(['refreshPage'])

const visible = ref(false)
const dataFormRef = ref()
const baseClassList = ref<any[]>([])

const dataForm = reactive({
  id: '',
  tableName: '',
  tableComment: '',
  className: '',
  baseClassId: '',
  moduleName: '',
  functionName: '',
  packageName: '',
  version: '',
  author: '',
  email: '',
  generatorType: 0,
  formLayout: 1,
  backendPath: '',
  frontendPath: '',
})

const rules = reactive({
  tableName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  tableComment: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  className: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  packageName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  author: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  moduleName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  functionName: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  generatorType: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  formLayout: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  backendPath: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
  frontendPath: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
})

const init = (id: string) => {
  visible.value = true
  dataForm.id = ''

  // 重置表单数据
  if (dataFormRef.value) {
    dataFormRef.value.resetFields()
  }

  getTable(id)
  getBaseClassList()
}

const getTable = (id: string) => {
  tableApi(id).then((res) => {
    Object.assign(dataForm, res.data)
  })
}

const getBaseClassList = () => {
  baseClassListApi().then((res) => {
    baseClassList.value = res.data
  })
}

/** 保存 */
const handleSubmit = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false
    }

    updateTable(dataForm).then(() => {
      visible.value = false
      emit('refreshPage')
      ElMessage.success('操作成功')
    })
  })
}

/** 生成代码 */
const handleGen = () => {
  dataFormRef.value.validate(async (valid: boolean) => {
    if(!valid){
      return false;
    }

    // 先保存
    await updateTable(dataForm)

    // 生成代码，zip压缩包
    if(dataForm.generatorType === 0){
      downloadZip([dataForm.id])
      visible.value = false
      return
    }

    // 生成代码，自定义路径
    custom([dataForm.id]).then(() => {
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
