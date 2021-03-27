package io.itjun.business.service;

import lombok.SneakyThrows;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-19 16:21
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    @HmilyTCC(confirmMethod = "confirmSay", cancelMethod = "cancelSay")
    @SneakyThrows
    public Boolean say(int value) {
        if (value == 0) {
            throw new Exception();
        } else {
            System.out.println("hello");
        }
        return false;

    }

    @Override
    public Boolean cancelSay(int value) {
        System.out.println("cancelSay");
        return false;

    }

    @Override
    public Boolean confirmSay(int value) {
        System.out.println("confirmSay");
        return false;
    }
}
