package io.itjun.router;

import io.itjun.router.load.WeightAddress;

import java.util.ArrayList;
import java.util.List;

public class BaiduRouter implements IRouter {

    @Override
    public String getPrefix() {
        return "/baidu/";
    }

    @Override
    public boolean isRemovePrefix() {
        return true;
    }

    @Override
    public List<String> getAddress() {
        List<String> list = new ArrayList<>();
        list.add("http://www.baidu.com");
        return list;
    }

    @Override
    public List<WeightAddress> getWeightAddress() {
        return new ArrayList<>();
    }

}
