package io.itjun.kafka.server;

/**
 * @describe 用topic 模拟一个用户注册推送的场景
 */
public interface UserRegisteredService {
    /**
     * @param username 用户名称
     * @describe 用户注册
     */
    String register(String username);

}
