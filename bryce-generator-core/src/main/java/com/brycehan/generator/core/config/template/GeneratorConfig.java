package com.brycehan.generator.core.config.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 代码生成器配置
 *
 * @author Bryce Han
 * @since 2023/4/28
 */
@Slf4j
public class GeneratorConfig {

    private String template;

    public GeneratorConfig(String template) {
        this.template = template;
    }

    public GeneratorContent getGeneratorContent() {
        // 模板路径，如果不是以/结尾，则添加/
        if (!StringUtils.endsWith(template, "/")) {
            template = template.concat("/");
        }
        // 模板配置文件
        InputStream config = this.getClass().getResourceAsStream(template.concat("config.json"));
        if (Objects.isNull(config)) {
            throw new RuntimeException("模板配置文件，config.json不存在");
        }
        // 读取模板配置文件
        try {
            String configContent = StreamUtils.copyToString(config, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            GeneratorContent generatorContent = objectMapper.readValue(configContent, GeneratorContent.class);
            for (TemplateVo templateVoItem : generatorContent.getTemplates()) {
                // 模板文件
                InputStream templateStream = this.getClass().getResourceAsStream(template.concat(templateVoItem.getTemplateName()));
                if (Objects.isNull(templateStream)) {
                    throw new RuntimeException("模板文件".concat(templateVoItem.getTemplateName()).concat("不存在"));
                }
                // 读取模板内容
                String templateContent = StreamUtils.copyToString(templateStream, StandardCharsets.UTF_8);
                templateVoItem.setTemplateContent(templateContent);
            }
            return generatorContent;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("读取config.json配置文件失败");
        }
    }
}
