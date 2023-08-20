import request from '@/utils/request'

export const addOrUpdate = (data: any) => {
  if (data.id) {
    return request.patch('/gen/datasource', data)
  } else {
    return request.post('/gen/datasource', data)
  }
}

export const deleteByIds = (ids: string[]) => {
  return request.delete(`/gen/datasource`, { data: { ids } })
}

export const getById = (id: string) => {
  return request.get(`/gen/datasource/${id}`)
}

export const testById = (id: string) => {
  return request.get(`/gen/datasource/test/${id}`)
}

export const list = () => {
  return request.get('/gen/datasource/list')
}

/** 要生成代码的表列表 */
export const tableList = (id: string) => {
  return request.get(`/gen/datasource/table/list/${id}`)
}

export const page = (body: any) => {
  return request.post('/gen/datasource/page', body)
}
