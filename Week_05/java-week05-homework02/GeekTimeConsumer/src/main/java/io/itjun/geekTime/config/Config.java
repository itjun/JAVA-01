package io.itjun.geekTime.config;

import io.itjun.geektime.Clazz;
import org.springframework.context.annotation.Bean;

//@Configuration
public class Config {
    @Bean
    public Clazz clazz() {
        return new Clazz("一班", 45);
    }
}
