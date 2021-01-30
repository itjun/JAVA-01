package io.itjun.config;

import io.itjun.router.IRouter;
import io.itjun.router.NotFoundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 路由设置
 */
public class RouterConfig {

    public static Map<String, IRouter> routers = new HashMap<>();
    public static List<String> list = new ArrayList<>();

    public void config(FullHttpRequest request, ChannelHandlerContext context) {
        for (int i = 0; i < list.size(); i++) {
            if (request.uri().contains(list.get(i))) {
                routers.get(list.get(i)).route(request, context);
                return;
            }
        }
        new NotFoundHandler().route(request, context);
    }

    /**
     * <p> Url对比 </p>
     */
    public static void sortMapList() {
        List<String> realList = new ArrayList<>(routers.keySet());
        List<String> sortList = new ArrayList<>();
        for (int i = 0; i < realList.size(); i++) {
            int index = -1;
            for (int j = 0; j < realList.size(); j++) {
                if (realList.get(i).length() < realList.get(j).length()) {
                    index = j;
                }
            }
            if (index != -1) {
                sortList.add(i, realList.get(index));
                realList.set(index, "");
            } else {
                sortList.add(i, realList.get(i));
                realList.set(i, "");
            }
        }
        list = sortList;
    }
}
