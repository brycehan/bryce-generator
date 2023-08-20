import request from '@/utils/request'
import download from "@/utils/download";

/** 生成代码（zip压缩包格式） */
export const downloadZip = (tableIds: string[]) => {
    download.post('/gen/generator/download', tableIds)
}

/** 生成代码（自定义目录） */
export const custom = (tableIds: string[]) => {
    return request.post('/gen/generator/custom', tableIds)
}