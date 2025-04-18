package com.brycehan.boot.generator.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.brycehan.boot.generator")
public class BryceGeneratorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BryceGeneratorServerApplication.class, args);
    }

}
