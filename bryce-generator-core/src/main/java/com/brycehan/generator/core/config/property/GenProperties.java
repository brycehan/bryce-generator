package com.brycehan.generator.core.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 生成器属性
 *
 * @author brycehan
 * @since 2023/8/26
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bryce.gen")
public class GenProperties {

    /**
     * 表前缀（生成类名不会包含表前缀，多个用逗号分隔）
     */
    private String tablePrefix = "brc_";

}
