<template>
  <el-card shadow="hover">
    <el-form :inline="true" :model="state.queryForm" @keyup.enter="getPage()" @submit.prevent>
<#list queryList as field>
      <el-form-item>
      <#if field.queryFormType == 'text' || field.queryFormType == 'textarea' || field.queryFormType == 'editor'>
        <el-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}" />
      <#elseif field.queryFormType == 'select'>
        <#if field.formDict??>
        <dict-select v-model="state.queryForm.${field.attrName}" dict-type="${field.formDict}" placeholder="${field.fieldComment!}" clearable/>
        <#else>
        <el-select v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}">
          <el-option label="选择" value="0"/>
        </el-select>
        </#if>
      <#elseif field.queryFormType == 'radio'>
        <#if field.formDict??>
          <dict-radio-group v-model="state.queryForm.${field.attrName}" dict-type="${field.formDict}" />
        <#else>
          <el-radio-group v-model="state.queryForm.${field.attrName}">
            <el-radio label="选择" value="0"/>
          </el-radio-group>
        </#if>
      <#elseif field.queryFormType == 'date'>
        <el-date-picker
          v-model="state.queryForm.${field.attrName}"
          type="daterange"
          value-format="YYYY-MM-DD"/>
      <#elseif field.queryFormType == 'datetime'>
        <el-date-picker
          v-model="state.queryForm.${field.attrName}"
          type="datetimerange"
          value-format="YYYY-MM-DD HH:mm"/>
      <#else>
        <el-input v-model="state.queryForm.${field.attrName}" placeholder="${field.fieldComment!}" />
      </#if>
      </el-form-item>
</#list>
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
      <el-table-column type="selection" header-align="center" align="center" width="50" />
    <#list gridList as field>
      <#if field.formDict??>
      <dict-table-column label="${field.fieldComment!}" prop="${field.attrName}" dict-type="${field.formDict}" />
      <#else>
      <el-table-column label="${field.fieldComment!}" prop="${field.attrName}" header-align="center" align="center" />
      </#if>
    </#list>
      <el-table-column label="操作" fixed="right" header-align="center" align="center" width="150">
        <template #default="scope">
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

    <!-- 弹窗，新增 / 修改 -->
    <AddOrEdit ref="addOrEditRef" @refresh-page="getPage" />
  </el-card>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import AddOrEdit from './add-or-edit.vue'
import { page, deleteByIds } from '@/api/${moduleName}/${functionName}'
import { ElMessage, ElMessageBox } from 'element-plus'

const state = reactive({
    queryForm: {
        <#list queryList as field>
          ${field.attrName}: ''<#sep>,</#sep>
        </#list>
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
</script>
