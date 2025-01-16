package com.brycehan.boot.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.brycehan.boot.generator.service.GeneratorService;
import com.brycehan.boot.generator.common.ResponseResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


/**
 * 表管理控制器
 *
 * @author Bryce Han
 * @since 2023/4/27
 */
@RequestMapping("/generator-ui/gen/generator")
@RestController
@RequiredArgsConstructor
public class GeneratorController {

    private final GeneratorService generatorService;

    /**
     * 预览代码
     *
     * @param tableId 表ID
     * @return 代码数据
     */
    @GetMapping(path = "/preview/{tableId}")
    public ResponseResult<Map<String, String>> preview(@PathVariable Long tableId) {
        Map<String, String> data = generatorService.previewCode(tableId);
        return ResponseResult.ok(data);
    }

    /**
     * 生成代码，zip压缩包方式
     *
     * @param tableIds 表IDs
     */
    @PostMapping(path = "/download")
    public void download(HttpServletResponse response, @RequestBody List<Long> tableIds) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        // 生成代码
        for (Long tableId : tableIds) {
            generatorService.downloadCode(tableId, zip);
        }
        IoUtil.close(zip);

        // zip 压缩包数据
        byte[] data = outputStream.toByteArray();
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename*=utf-8''bryce.zip");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);

        try {
            IoUtil.write(response.getOutputStream(), false, data);
        } catch (IOException e) {
            throw new RuntimeException("生成代码，zip压缩包方式失败", e);
        }
    }

    /**
     * 生成代码，自定义目录方式
     *
     * @param tableIds 表IDs
     * @return 响应结果
     */
    @PostMapping(path = "/custom")
    public ResponseResult<Void> custom(@RequestBody Long[] tableIds) {
        for (Long tableId : tableIds) {
            generatorService.generatorCode(tableId);
        }
        return ResponseResult.ok();
    }

}

