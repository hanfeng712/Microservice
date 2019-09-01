package com.gaoxi.gaoxiredis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.gaoxi.gaoxicommonservicefacade.dao")
public class GaoxiRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaoxiRedisApplication.class, args);
    }

}
