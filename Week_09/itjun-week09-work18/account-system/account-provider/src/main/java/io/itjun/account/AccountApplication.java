package io.itjun.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-16 14:41
 * 账户应用启动
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(value = "pers.peixinyi.account.mapper")
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
