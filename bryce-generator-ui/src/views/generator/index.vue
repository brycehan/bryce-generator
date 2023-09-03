<template>
  <el-card shadow="hover">
    <el-form :inline="true" :model="state.queryForm" @keyup.enter="getPage()" @submit.prevent>
      <el-form-item>
        <el-input v-model="state.queryForm.tableName" placeholder="表名" />
      </el-form-item>
      <el-form-item>
          <el-button @click="getPage()">查询</el-button>
          <el-button type="primary" @click="handleImport()">导入</el-button>
          <el-button type="success" @click="handleDownloadBatch()">生成代码</el-button>
          <el-button type="danger" @click="handleDeleteBatch()">删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="state.loading"
      :data="state.data"
      :border="true"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column label="表名" prop="tableName" header-align="center" align="center"></el-table-column>
      <el-table-column label="表说明" prop="tableComment" header-align="center" align="center"></el-table-column>
      <el-table-column label="类名" prop="className" header-align="center" align="center"></el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="280">
        <template #default="scope">
          <el-button type="success" link @click="handleGenerator(scope.row.id)">生成代码</el-button>
          <el-button type="info" link @click="handlePreview(scope.row.id)">预览</el-button>
          <el-button type="primary" link @click="handleEdit(scope.row.id)">编辑</el-button>
          <el-button type="warning" link @click="handleSync(scope.row)">同步</el-button>
          <el-button type="danger" link @click="handleDeleteBatch(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="state.page.current"
      :page-size="state.page.pageSize"
      :page-sizes="[10, 20, 50, 100, 200]"
      :total="state.page.total"
      layout="total,sizes,prev,pager,next,jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!-- 弹窗 -->
    <Import ref="importRef" @refresh-page="getPage" />
    <Edit ref="editRef" @refresh-page="getPage" />
    <Preview ref="previewRef" />
    <Generator ref="generatorRef" @refresh-page="getPage" />
  </el-card>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import Edit from '@/views/generator/edit.vue'
import Preview from '@/views/generator/preview.vue'
import Import from '@/views/generator/import.vue'
import Generator from '@/views/generator/generator.vue'
import { page, deleteByIds, syncTable } from '@/api/table'
import { ElMessage, ElMessageBox } from 'element-plus'
import {downloadZip} from "@/api/generator";

const state = reactive({
  queryForm: {
    tableName: '',
  },
  page: {
    /** 起始页数，从1开始计算 */
    current: 1,
    /** 每页条数 */
    pageSize: 10,
    /** 总条数 */
    total: 0,
    /** 排序项 */
    orderItems: [{column: 'createTime', asc: false}],
  },
  data: [],
  tableIds: [] as any[],
  loading: false,
})

const editRef = ref()
const importRef = ref()
const previewRef = ref()
const generatorRef = ref()

onMounted(() => {
  getPage()
})

const getPage = (current: number = 1, size?: number) => {
  state.page.current = current

  if(size !== undefined){
    state.page.pageSize = size
  }
  state.loading = true

  const body = {
    current: state.page.current,
    pageSize: state.page.pageSize,
    orderItems: state.page.orderItems,
    ...state.queryForm
  }

  page(body).then((response: any) => {
    const { list, total } = response.data
    state.data = list
    state.page.total = total
  })
  state.loading = false
}

/** 调整当前显示条数 */
const handleSizeChange = (size: number) => {
  getPage(1, size)
}

/** 跳转到指定页 */
const handleCurrentChange = (current: number) => {
  getPage(current)
}

const handleGenerator = (id: string) => {
  generatorRef.value.init(id)
}

const handlePreview = (id: string) => {
  previewRef.value.init(id)
}

const handleEdit = (id?: string) => {
  editRef.value.init(id)
}

const handleSync = (row: any) => {
  ElMessageBox.confirm(`确定同步数据表${row.tableName}吗？`, '提示', {
    type: 'warning'
  }).then(() => {
    syncTable(row.id).then(() => {
      ElMessage.success('同步成功')
    })
  }).catch(() => {})
}

const handleImport = () => {
  importRef.value.init()
}

const handleDownloadBatch = () => {
  if(state.tableIds.length === 0){
    ElMessage.warning('请选择生成代码的表')
    return
  }
  downloadZip(state.tableIds)
}

const handleDeleteBatch = (id?: string) => {
  let data: any[] = []
  if (id) {
    data = [id]
  } else {
    data = state.tableIds ? state.tableIds : []
    if (state.tableIds.length === 0) {
      ElMessage.warning('请选择删除的记录')
      return
    }
  }
  ElMessageBox.confirm('确定进行删除操作？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      deleteByIds(data).then(() => {
        ElMessage.success('删除成功')
        getPage()
      })
    })
    .catch((error) => {
      console.error(error)
    })
}

const handleSelectionChange = (selections: any[]) => {
  state.tableIds = selections.map((item: any) => item.id)
}

</script>
