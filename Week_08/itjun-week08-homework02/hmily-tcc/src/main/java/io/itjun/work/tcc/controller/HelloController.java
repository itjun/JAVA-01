package io.itjun.work.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.itjun.work.tcc.service.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping
    public void Hello(String hello, int value) {
        System.out.println(String.format("%s - %d", hello, value));
        helloService.say(hello, value);
    }
}
