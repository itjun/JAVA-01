package io.itjun.week07;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Random;

public class RandomIdGenerateImpl implements RandomIdGenerate {

    private String LOCAL_HOST;
    private String SERVER_NAME;

    public RandomIdGenerateImpl() {
        this.LOCAL_HOST = getLocalHost();
        this.SERVER_NAME = getLocalServerName();
    }

    @Override
    public String generate() {
        return String.format("%s-%s-%s-%s", LOCAL_HOST, SERVER_NAME, getDateStamp(), GenerateRandomAlphameric(6));
    }

    /**
     * 获取本地IP
     *
     * @return
     */
    private String getLocalHost() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "255.255.255.255";
        }
    }

    /**
     * 获取当前服务器名称
     *
     * @return
     */
    private String getLocalServerName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "unknown";
        }
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    private String getDateStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 获取随机String
     *
     * @return
     */
    private String GenerateRandomAlphameric(int length) {
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return String.valueOf(randomChars);
    }
}
