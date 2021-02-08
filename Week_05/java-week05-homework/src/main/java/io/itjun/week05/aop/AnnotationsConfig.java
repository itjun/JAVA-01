package io.itjun.week05.aop;

import io.itjun.week05.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AnnotationsConfig {

    @Bean
    public Student aop1() {
        return new Student(1024, "itjun-aop-01", "3班");
    }

    @Bean(name = "itjun-aop-02")
    public Student apo2() {
        return new Student(2048, "itjun-aop-02", "3班");
    }

}
