package io.itjun.config;

import io.itjun.filter.IFilter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.List;

public class FilterConfig {

    /**
     * 过滤器列表，数字越低，优先级高
     */
    public static List<IFilter> list = new ArrayList<>();

    public void before(FullHttpRequest request) {
        for (IFilter filter : list) {
            request = filter.before(request);
        }
    }

    public void after(FullHttpRequest request) {
        for (int i = list.size(); i > 0; i--) {
            request = list.get(i - 1).after(request);
        }
    }

}
