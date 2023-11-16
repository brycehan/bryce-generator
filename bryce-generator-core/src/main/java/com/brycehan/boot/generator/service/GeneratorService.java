package com.brycehan.boot.generator.service;

import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成服务
 *
 * @author Bryce Han
 * @since 2023/5/11
 */
public interface GeneratorService {

    /**
     * 预览代码
     *
     * @param tableId 表ID
     * @return 代码数据
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * 生成生成的代码
     *
     * @param tableId 表ID
     * @param zip     Zip输出流
     */
    void downloadCode(Long tableId, ZipOutputStream zip);

    /**
     * 生成代码
     *
     * @param tableId 表ID
     */
    void generatorCode(Long tableId);

}
