package pers.peixinyi.geektime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "class")
@PropertySource("classpath:application.yml")
public class Clazz implements InitializingBean {
    private String classNo;
    private int classSize;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化Class 班级:" + classNo + "\t班级人数:" + classSize);
    }
}
