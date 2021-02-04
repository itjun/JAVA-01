package io.itjun;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class Stupid {

    public int hashCode() {
        return new Random(100_0000).nextInt();
    }

    public boolean equals(Object obj) {
        return this.getClass().isInstance(obj);
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        HashMap<Stupid, Stupid> map = new HashMap<>();
        IntStream.range(1, 100_10000).forEach(i -> {
            map.put(new Stupid(), new Stupid());
        });
        System.out.println(map.size());
    }

}
