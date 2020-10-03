package com.xinggevip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xinggevip.dao")
public class MycrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycrmApplication.class, args);
    }

}
