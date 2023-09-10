import request from '@/utils/request'

export const saveOrUpdate = (data: any) => {
  if (data.id) {
    return request.put('/gen/baseClass', data)
  } else {
    return request.post('/gen/baseClass', data)
  }
}

export const deleteByIds = (ids: string[]) => {
  return request.delete(`/gen/baseClass`, { data: { ids } })
}

export const getById = (id: string) => {
  return request.get(`/gen/baseClass/${id}`)
}

export const list = () => {
  return request.get('/gen/baseClass/list')
}

export const page = (body: any) => {
  return request.post('/gen/baseClass/page', body)
}
