package io.itjun.week05;

import io.itjun.week05.bean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConfigDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Student student01 = (Student) context.getBean("itjun-xml-01");
        System.out.println(student01);

        Student student02 = (Student) context.getBean("itjun-xml-02");
        System.out.println(student02);
    }

}
