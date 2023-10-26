package com.brycehan.generator.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 生成器属性
 *
 * @author brycehan
 * @since 2023/8/26
 */
@Data
@ConfigurationProperties(prefix = "bryce.generator")
public class GeneratorProperties {

    /**
     * 表前缀（生成类名不会包含表前缀，多个用逗号分隔）
     */
    private String tablePrefix = "brc_";

    /**
     * 模板路径
     */
    private String template = "/templates";

}
