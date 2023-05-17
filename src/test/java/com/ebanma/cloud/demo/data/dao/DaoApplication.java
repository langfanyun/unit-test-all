package com.ebanma.cloud.demo.data.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dao 应用类
 */
@SpringBootApplication(scanBasePackages = {"com.ebanma.cloud.demo.data.dao"})
public class DaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DaoApplication.class, args);
    }
}