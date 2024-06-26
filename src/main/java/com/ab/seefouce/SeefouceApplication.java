package com.ab.seefouce;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ab.seefouce.domain.*.mapper")
public class SeefouceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeefouceApplication.class, args);
    }

}
