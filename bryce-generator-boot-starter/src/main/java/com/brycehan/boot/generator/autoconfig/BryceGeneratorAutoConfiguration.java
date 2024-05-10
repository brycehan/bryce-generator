package com.brycehan.boot.generator.autoconfig;

import com.brycehan.boot.generator.config.GeneratorProperties;
import com.brycehan.boot.generator.config.template.GeneratorConfig;
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
@ComponentScan(basePackages = "com.brycehan.boot.generator")
@MapperScan(basePackages = "com.brycehan.boot.generator.mapper")
@EnableConfigurationProperties(GeneratorProperties.class)
public class BryceGeneratorAutoConfiguration {

	@Bean
	GeneratorConfig generatorConfig(GeneratorProperties properties) {
		return new GeneratorConfig(properties.getTemplate());
	}

}
