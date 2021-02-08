package io.itjun.week05.auto;

import org.springframework.stereotype.Component;

@Component
public class AutowiredConfig {

    public void hello() {
        System.out.println("你好");
    }

}
