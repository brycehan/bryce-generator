package com.brycehan.generator.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 生成器属性
 *
 * @author Bryce Han
 * @since 2023/4/28
 */
@Data
@ConfigurationProperties(prefix = "bryce.generator")
public class BryceGeneratorProperties {

    /**
     * 模板路径
     */
    private String template = "/templates";
}
