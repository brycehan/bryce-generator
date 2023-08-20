package com.brycehan.generator.autoconfig;

import com.brycehan.generator.core.config.template.GeneratorConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 生成器自动配置
 *
 * @author brycehan
 * @since 2023/4/28
 */
@RequiredArgsConstructor
@Configuration
@ComponentScan(basePackages = "com.brycehan.generator")
@EnableConfigurationProperties(BryceGeneratorProperties.class)
public class BryceGeneratorAutoConfiguration {

	private final BryceGeneratorProperties properties;

	@Bean
	GeneratorConfig generatorConfig() {
		return new GeneratorConfig(properties.getTemplate());
	}

}
