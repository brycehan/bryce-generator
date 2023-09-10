<template>
  <el-card shadow="hover">
    <el-form :inline="true" :model="state.queryForm" @keyup.enter="getPage()" @submit.prevent>
      <el-form-item>
        <el-input v-model="state.queryForm.connName" placeholder="连接名" />
      </el-form-item>
      <el-form-item prop="dbType">
        <el-select v-model="state.queryForm.dbType" placeholder="数据库类型" clearable>
          <el-option value="MySQL" label="MySQL"></el-option>
          <el-option value="PostgreSQL" label="PostgreSQL"></el-option>
          <el-option value="Oracle" label="Oracle"></el-option>
          <el-option value="SQLServer" label="SQLServer"></el-option>
          <el-option value="DM" label="达梦8"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getPage()">查询</el-button>
        <el-button type="primary" @click="handleAddOrEdit()">新增</el-button>
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
      <el-table-column label="连接名" prop="connName" header-align="center" align="center"></el-table-column>
      <el-table-column label="数据库类型" prop="dbType" header-align="center" align="center"></el-table-column>
      <el-table-column label="数据库URL" prop="connUrl" show-overflow-tooltip header-align="center" align="center"></el-table-column>
      <el-table-column label="用户名" prop="username" header-align="center" align="center"></el-table-column>
      <el-table-column label="密码" header-align="center" align="center">
        <template #default="scope">
          <span>{{ formatPassword(scope.row.password) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="170">
        <template #default="scope">
          <el-button type="success" link @click="handleDatasourceTest(scope.row.id)"
            >测试</el-button
          >
          <el-button type="primary" link @click="handleAddOrEdit(scope.row.id)">编辑</el-button>
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

    <!-- 弹窗，新增/修改 -->
    <AddOrEdit ref="addOrEditRef" @refresh-page="getPage" />
  </el-card>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from 'vue'
import AddOrEdit from '@/views/datasource/add-or-edit.vue'
import {deleteByIds, page, testById} from '@/api/datasource'
import {ElMessage, ElMessageBox} from 'element-plus'

const state = reactive({
  queryForm: {
    connName: '',
    dbType: ''
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
  dataSelections: [] as any[],
  loading: false,
})

const addOrEditRef = ref()

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

const handleDatasourceTest = (id: string) => {
  testById(id).then((response: any) => {
    ElMessage.success(response.data)
  })
}

const handleAddOrEdit = (id?: string) => {
  addOrEditRef.value.init(id)
}

const handleDeleteBatch = (id?: string) => {
  let data: any[] = []
  if (id) {
    data = [id]
  } else {
    data = state.dataSelections ? state.dataSelections : []
    if (data.length === 0) {
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
  state.dataSelections = selections.map((item: any) => item.id)
}

/** 格式化密码 */
const formatPassword = (string: string) => {
  return Array(string.length).fill('•').join('')
}
</script>
