package io.itjun.config;

import io.itjun.filter.FilterOne;
import io.itjun.filter.FilterTwo;
import io.itjun.router.BaiduRouter;
import io.itjun.router.GatewayRouter;
import io.itjun.router.OrderRouter;

public class AppConfig {

    /**
     * 载入过滤器配置
     */
    public static void loadFilter() {
        FilterConfig.list.add(0, new FilterTwo());
        FilterConfig.list.add(1, new FilterOne());
    }

    /**
     * 载入路由器配置
     */
    public static void loadRouter() {
        BaiduRouter baidu = new BaiduRouter();
        RouterConfig.routers.put(baidu.getPrefix(), baidu);

        GatewayRouter gatewayRouter = new GatewayRouter();
        RouterConfig.routers.put(gatewayRouter.getPrefix(), gatewayRouter);

        OrderRouter qimen = new OrderRouter();
        RouterConfig.routers.put(qimen.getPrefix(),qimen);

        RouterConfig.sortMapList();
    }

}
