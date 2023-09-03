package com.brycehan.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.brycehan.generator.core.util.TemplateUtils;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Bryce Han
 * @since 2023/5/26
 */
@Slf4j
public class MdTests {

    @Test
    void mdTest() throws IOException {
        String name = "/templates/md.ftl";
        String filename = "test.md";

        // 读取模板配置文件
        InputStream config = TemplateUtils.class.getResourceAsStream(name);
        if (Objects.isNull(config)) {
            throw new RuntimeException("模板配置文件，config.json不存在");
        }
        String configContent = StreamUtils.copyToString(config, StandardCharsets.UTF_8);

        Map<String, Object> map = new HashMap<>();
        map.put("size", 168);

        StringReader reader = new StringReader(configContent);
        StringWriter writer = new StringWriter();

        try {
            Template template = new Template(name, reader, null, "utf-8");
            template.process(map, writer);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("渲染模板失败，请检查模板语法", e);
        }
        String content = writer.toString();
        IoUtil.close(reader);
        IoUtil.close(writer);

        FileUtil.writeUtf8String(content, filename);
    }
}
