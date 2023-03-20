package com.kh.qp.qp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kh.qp.qp.mapper")
public class QuestPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestPlatformApplication.class, args);
    }

}
