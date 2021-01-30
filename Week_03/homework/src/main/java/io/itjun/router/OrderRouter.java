package io.itjun.router;

import io.itjun.router.load.WeightAddress;

import java.util.ArrayList;
import java.util.List;

public class OrderRouter implements IRouter {

    @Override
    public String getPrefix() {
        return "/qimen/";
    }

    @Override
    public boolean isRemovePrefix() {
        return true;
    }

    @Override
    public List<String> getAddress() {
        List<String> list = new ArrayList<>();
        list.add("https://qimen.diteng.site/public");
        return list;
    }

    @Override
    public List<WeightAddress> getWeightAddress() {
        return new ArrayList<>();
    }

}
