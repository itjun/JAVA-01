package io.itjun.router;

import io.itjun.router.load.WeightAddress;

import java.util.List;
import java.util.Random;

/**
 * 负载均衡策略
 */
public class LoadBalancing {

    private static int index;

    /**
     * <p> 随机负载 </p>
     *
     * @param address
     */
    public static String random(String[] address) {
        return address[new Random().nextInt(address.length)];
    }

    /**
     * <p> 轮询分发 </p>
     *
     * @param address
     */
    public static String round(List<String> address) {
        index = index % address.size();
        return address.get(index);
    }

    /**
     * <p> 权重分配 </p>
     *
     * @param weightAddresses
     */
    public static String weight(List<WeightAddress> weightAddresses) {
        int ratioMax = 0;
        for (WeightAddress weightAddress : weightAddresses) {
            ratioMax += weightAddress.getWeight();
        }
        int index = new Random().nextInt(ratioMax);
        int indexRatio = 0;
        for (int i = 0; i < weightAddresses.size(); i++) {
            if (i == 0) {
                if (index < weightAddresses.get(i).getWeight()) {
                    return weightAddresses.get(i).getAddress();
                }
                indexRatio = indexRatio + weightAddresses.get(i).getWeight();
            } else {
                if (index < (indexRatio = indexRatio + weightAddresses.get(i).getWeight())) {
                    return weightAddresses.get(i).getAddress();
                }
            }
        }
        return null;
    }

}
