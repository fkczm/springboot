package com.abc.demo.easyExcle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.abc.demo.easyExcle.mapper")
public class TestalieasyexcleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestalieasyexcleApplication.class, args);
    }

}
