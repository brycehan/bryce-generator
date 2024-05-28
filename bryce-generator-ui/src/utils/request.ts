import axios from 'axios'
import { ElMessage } from 'element-plus'
import qs from 'qs'

/** axios实例 */
const request = axios.create({
  baseURL: import.meta.env.VITE_API_URL as any,
  timeout: 60000,
  headers: { 'Content-Type': 'application/json;charset=UTF-8' }
})

/** 请求拦截器 */
request.interceptors.request.use(
  (config) => {
    config.headers['Accept-Language'] = 'zh-CN'
    config.headers['X-Source-Client'] = 'pc'
    if (Object.values(config.headers).includes('application/x-www-form-urlencoded')) {
      config.data = qs.stringify(config.data)
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

/** 响应拦截器 */
request.interceptors.response.use(
  (response) => {
    if (response.status !== 200) {
      return Promise.reject(new Error(response.statusText) || 'Error')
    }

    if (response.headers['content-type'] === 'application/json') {
        const responseData = response.data
        // 响应成功
        if (responseData.code === 200) {
            return responseData
        }

        // 错误提示
        ElMessage.error(responseData.message|| 'Error')

        return Promise.reject(new Error(responseData.message || 'Error'))
    }else {
        return response
    }

  },
  (error) => {
    ElMessage.error(error.message || 'Error')
    return Promise.reject(error)
  }
)

export default request
