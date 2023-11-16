package com.brycehan.boot.generator.util;

import cn.hutool.core.io.IoUtil;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * 模板工具类
 *
 * @author Bryce Han
 * @since 2023/5/11
 */
@Slf4j
public class TemplateUtils {

    /**
     * 获取模板渲染后的内容
     *
     * @param content   模板内容
     * @param dataModel 数据模型
     * @return 模板渲染后的内容
     */
    public static String getContent(String content, Map<String, Object> dataModel) {
        if (dataModel.isEmpty()) {
            return content;
        }

        StringReader reader = new StringReader(content);
        StringWriter writer = new StringWriter();
        String templateName = dataModel.get("templateName").toString();
        try {
            Template template = new Template(templateName, reader, null, "utf-8");
            template.process(dataModel, writer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("渲染模板失败，请检查模板语法", e);
        }
        content = writer.toString();
        IoUtil.close(reader);
        IoUtil.close(writer);
        return content;
    }

}
