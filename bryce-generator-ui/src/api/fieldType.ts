import request from '@/utils/request'

export const saveOrUpdate = (data: any) => {
  if (data.id) {
    return request.put('/gen/fieldType', data)
  } else {
    return request.post('/gen/fieldType', data)
  }
}

export const deleteByIds = (ids: string[]) => {
  return request.delete(`/gen/fieldType`, { data: { ids } })
}

export const getById = (id: string) => {
  return request.get(`/gen/fieldType/${id}`)
}

export const list = () => {
  return request.get('/gen/fieldType/list')
}

export const page = (body: any) => {
  return request.post('/gen/fieldType/page', body)
}
