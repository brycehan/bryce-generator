import request from '@/utils/request'

/**
 * 保存${tableComment}
 *
 * @param data 参数
 */
export const saveOrUpdateApi = (data: any) => {
    if (data.id) {
        return request.put('/${moduleName}/${functionName}', data)
    } else {
        return request.post('/${moduleName}/${functionName}', data)
    }
}

/**
 * 删除${tableComment}
 *
 * @param ids ID数组
 */
export const deleteByIdsApi = (ids: bigint[]) => {
    return request.delete('/${moduleName}/${functionName}', { data: { ids } })
}

/**
 * 查询${tableComment}详情
 *
 * @param id ID
 */
export const getByIdApi = (id: bigint) => {
    return request.get(`/${moduleName}/${functionName}/<#noparse>${id}</#noparse>`)
}

/**
 * ${tableComment}分页查询
 *
 * @param data 分页参数
 */
export const postPageApi = (data: any) => {
    return request.post('/${moduleName}/${functionName}/page', data)
}
