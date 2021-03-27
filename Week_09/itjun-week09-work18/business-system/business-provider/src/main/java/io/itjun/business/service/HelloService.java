package io.itjun.business.service;

import org.dromara.hmily.annotation.Hmily;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-19 16:20
 */
public interface HelloService {
    @Hmily
    Boolean say(int value) throws Exception;

    @Hmily
    Boolean cancelSay(int value);

    @Hmily
    Boolean confirmSay(int value);
}
