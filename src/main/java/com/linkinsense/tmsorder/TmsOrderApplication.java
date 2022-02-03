package com.linkinsense.tmsorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.linkinsense.tmsorder.infrastructure.db.mapper")
@MapperScan(basePackages = "com.linkinsense.tmsorder.infrastructure.db.mapper")
public class TmsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmsOrderApplication.class, args);
    }

}
