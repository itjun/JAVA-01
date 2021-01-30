package io.itjun.router;

import io.itjun.router.load.WeightAddress;

import java.util.ArrayList;
import java.util.List;

public class GatewayRouter implements IRouter {

    @Override
    public String getPrefix() {
        return "/api/";
    }

    @Override
    public boolean isRemovePrefix() {
        return false;
    }

    @Override
    public List<String> getAddress() {
        List<String> list = new ArrayList<>();
        list.add("http://127.0.0.1:8088");
        return list;
    }

    @Override
    public List<WeightAddress> getWeightAddress() {
        return new ArrayList<>();
    }

}
