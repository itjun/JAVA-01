package io.itjun.kafka.notice;

import io.itjun.kafka.client.KafkaProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class RegisterNotice {

    public static final String registerTopic = "demo.user.register";

    @Resource
    KafkaProducer kafkaProducer;

    @After(value = "@annotation(io.itjun.kafka.notice.RegisterNoticeAnnotation)")
    public void notion(JoinPoint joinPoint) {
        String username = (String) joinPoint.getArgs()[0];
        System.out.println("用户" + username + "注册成功");
        kafkaProducer.send(registerTopic, username);
    }

}
