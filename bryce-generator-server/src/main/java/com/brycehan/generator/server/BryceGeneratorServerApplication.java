package com.brycehan.generator.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.brycehan.generator.*.mapper")
@ComponentScan(basePackages = "com.brycehan.generator")
@SpringBootApplication
public class BryceGeneratorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BryceGeneratorServerApplication.class, args);
    }

}
