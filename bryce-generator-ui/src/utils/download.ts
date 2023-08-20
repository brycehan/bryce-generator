import request from "@/utils/request"
import {ElMessage} from "element-plus";

/** 下载 */
const download = (config?: any) => {
    // 设置responseType 响应类型为blob，响应的直接是个blob对象
    request.request({
        ...config,
        responseType: 'blob'
    }).then(response => {

        const blob = new Blob([response.data])
        const reader = new FileReader();

        // 请求结果处理
        if (toString.call(response.data) === '[object Blob]') {
            reader.readAsDataURL(blob);
            reader.onload = function (e: any) {
                // 转换完成，创建一个a标签用于下载
                const a = document.createElement('a');
                const filename = decodeURI(response.headers['content-disposition']);
                a.download = filename.split("''")[1];
                a.href = e.target.result;
                // 在body中插入a元素
                document.body.insertAdjacentElement('afterend', a);
                a.click();
                a.remove();
            }
        } else {
            reader.readAsText(blob);
            reader.onload = function (e: any) {
                ElMessage.error('下载文件出现错误，请联系管理员！');
                console.error('generator.download', JSON.parse(e.target.result));
            }
        }
    })
}

const get = (url: string, config?: any) => {
    return download({
        url: url,
        method: "GET",
        ...config
    })
}

const post = (url: string, data?: any, config?: any) => {
    return download({
        url: url,
        method: "POST",
        data: data,
        ...config
    })
}

export default {get, post}