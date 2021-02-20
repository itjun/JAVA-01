package pers.peixinyi.geekTime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.peixinyi.geektime.Clazz;

//@Configuration
public class Config {
    @Bean
    public Clazz clazz() {
        return new Clazz("一班", 45);
    }
}
