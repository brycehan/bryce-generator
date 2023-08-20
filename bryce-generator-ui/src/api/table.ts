import request from '@/utils/request'

/** 导入表 */
export const importTableList = (datasourceId: string, tableNameList: string[]) => {
    return request.post(`/gen/table/import/${datasourceId}`, tableNameList)
}

export const update = (data: any) => {
    return request.put('/gen/table', data)
}

/** 更新表字段 */
export const updateFieldList = (tableId: string, fieldList: any[]) => {
    return request.put(`/gen/table/field/${tableId}`, fieldList)
}

/** 同步表 */
export const syncTable = (id: string) => {
    return request.get(`/gen/table/sync/${id}`)
}

export const deleteByIds = (ids: string[]) => {
    return request.delete(`/gen/table`, { data: { ids } })
}

export const getById = (id: string) => {
    return request.get(`/gen/table/${id}`)
}

export const page = (body: any) => {
    return request.post('/gen/table/page', body)
}
