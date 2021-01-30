package io.itjun.router.load;

/**
 * 权重分配
 */
public class WeightAddress {

    /**
     * 地址
     */
    private String address;

    /**
     * 占比
     */
    private int weight;

    public WeightAddress(String address, int weight) {
        this.address = address;
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RatioAddress{" +
                "address='" + address + '\'' +
                ", weight=" + weight +
                '}';
    }
}
