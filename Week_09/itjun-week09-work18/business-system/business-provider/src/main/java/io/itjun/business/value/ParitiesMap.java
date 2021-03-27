package io.itjun.business.value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-18 17:22
 */
public class ParitiesMap {

    private static Map<String, Double> parities = new HashMap<>();

    static {
        parities.put("Parities:0:1", 0.125);
        parities.put("Parities:1:0", 8D);
    }

    /**
     * 获取汇率
     *
     * @param currentMonetary
     * @param targetMonetary
     * @return
     */
    public static Double getParities(int currentMonetary, int targetMonetary) {
        String key = String.format("Parities:%d:%d", currentMonetary, targetMonetary);
        return parities.get(key);
    }

}
