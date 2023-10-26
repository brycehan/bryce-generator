<template>
  <el-dialog
    v-model="visible"
    title="导入数据库表"
    :close-on-click-modal="false"
    draggable
  >
    <el-form
      ref="dataFormRef"
      :model="dataForm"
      :rules="rules"
      @keyup.enter="handleSubmit()"
    >
      <el-form-item label="数据源" prop="datasourceId">
        <el-select v-model="dataForm.datasourceId" placeholder="请选择数据源" clearable @change="getTableList" style="width: 100%">
          <el-option label="默认数据源" :value="0" />
          <el-option v-for="datasource in dataForm.datasourceList" :key="datasource.id" :label="datasource.connName" :value="datasource.id" />
        </el-select>
      </el-form-item>
        <el-table
            :data="dataForm.tableList"
            :border="true"
            style="width: 100%"
            :max-height="400"
            @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
          <el-table-column label="表名" prop="tableName" header-align="center" align="center"></el-table-column>
          <el-table-column label="表说明" prop="tableComment" header-align="center" align="center"></el-table-column>
        </el-table>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit()">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { list as datasourceList, tableList } from '@/api/datasource'
import {importTableList} from "@/api/table";
import { ElMessage } from 'element-plus'

const emit = defineEmits(['refreshPage'])

const visible = ref(false)
const dataFormRef = ref()

const dataForm = reactive({
  id: '',
  tableNameListSelections: [] as any[],
  datasourceId: '',
  datasourceList: [] as any[],
  tableList: [] as any[],
  table: {
    tableName: ''
  }
})

const rules = reactive({
  datasourceId: [{ required: true, message: '必填项不能为空', trigger: 'blur' }],
})

const init = () => {
  visible.value = true
  dataForm.id = ''

  // 重置表单数据
  if (dataFormRef.value) {
    dataFormRef.value.resetFields()
  }

  dataForm.tableList = []

  getDatasourceList()
}

const getDatasourceList = () => {
  datasourceList().then(response => {
    dataForm.datasourceList = response.data
  })
}

const getTableList = () => {
  dataForm.table.tableName = ''
  tableList(dataForm.datasourceId).then(response => {
    dataForm.tableList = response.data
  })

}
/** 表单提交 */
const handleSubmit = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false
    }
    if(dataForm.tableNameListSelections.length === 0){
      ElMessage.warning('请选择记录')
      return
    }

    importTableList(dataForm.datasourceId, dataForm.tableNameListSelections).then(() => {
      visible.value = false
      emit('refreshPage')
      ElMessage.success('导入成功')
    })
  })
}

const handleSelectionChange = (selections: any[]) => {
  dataForm.tableNameListSelections = selections.map((item: any) => item.tableName)
}

defineExpose({
  init
})
</script>
