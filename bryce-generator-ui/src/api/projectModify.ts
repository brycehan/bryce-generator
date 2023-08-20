import request from '@/utils/request'
import download from "@/utils/download"

export const addOrUpdate = (data: any) => {
  if (data.id) {
    return request.put('/gen/projectModify', data)
  } else {
    return request.post('/gen/projectModify', data)
  }
}

export const deleteByIds = (ids: string[]) => {
  return request.delete('/gen/projectModify', { data: { ids } })
}

export const getById = (id: string) => {
  return request.get(`/gen/projectModify/${id}`)
}

export const page = (body: any) => {
  return request.post('/gen/projectModify/page', body)
}

/** 源码下载 */
export const sourceDownload = (id: string) => {
  download.get(`/gen/projectModify/download/${id}`)
}


