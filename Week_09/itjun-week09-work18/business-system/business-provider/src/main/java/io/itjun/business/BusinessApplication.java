package io.itjun.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-18 10:18
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan("pers.peixinyi.business.mapper")
public class BusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
