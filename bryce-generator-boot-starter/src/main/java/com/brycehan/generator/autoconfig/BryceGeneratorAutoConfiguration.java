package com.brycehan.generator.autoconfig;

import com.brycehan.generator.core.config.template.GeneratorConfig;
import org.mybatis.spring.annotation.MapperScan;
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
@Configuration
@ComponentScan(basePackages = "com.brycehan.generator")
@MapperScan(basePackages = "com.brycehan.generator.*.mapper")
@EnableConfigurationProperties(BryceGeneratorProperties.class)
public class BryceGeneratorAutoConfiguration {

	@Bean
	GeneratorConfig generatorConfig(BryceGeneratorProperties properties) {
		return new GeneratorConfig(properties.getTemplate());
	}

}
