package io.itjun.week05.aop;

import io.itjun.week05.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationsDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("io.itjun.week05");
        // 默认方式
        Student student = (Student) context.getBean("aop1");
        System.out.println(student);

        // 指定名称
        Student aop2 = (Student) context.getBean("itjun-aop-02");
        System.out.println(aop2);
    }

}
