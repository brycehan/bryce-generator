package com.brycehan.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.brycehan.generator.*.mapper")
@SpringBootApplication
public class BryceGeneratorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BryceGeneratorServerApplication.class, args);
    }

}
