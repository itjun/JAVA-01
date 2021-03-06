package io.itjun.geektime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//假如主模块有这个对象的时候就不创建该对象
//@ConditionalOnBean(Clazz.class)
@Configuration
public class ClazzBean {

    @Bean
    public Clazz clazz() {
        return new Clazz("未分配", 40);
    }

}
