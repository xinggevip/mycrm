package com.xinggevip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.xinggevip.dao")
//public class MycrmApplication extends SpringBootServletInitializer {
public class MycrmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MycrmApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(MycrmApplication.class);
//    }


}
