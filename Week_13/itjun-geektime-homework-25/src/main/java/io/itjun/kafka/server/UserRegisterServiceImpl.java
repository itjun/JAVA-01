package io.itjun.kafka.server;

import io.itjun.kafka.notice.RegisterNoticeAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterServiceImpl implements UserRegisteredService {

    @GetMapping(value = "register/{username}")
    @RegisterNoticeAnnotation
    public String register(@PathVariable String username) {
        System.out.println(getClass().getName() + " - " + username);
        return getClass().getName() + " - " + username;
    }

}
