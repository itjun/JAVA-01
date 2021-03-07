package io.itjun.mysql.proxy.service;

import io.itjun.mysql.proxy.entity.Order;

import javax.sql.DataSource;

public interface OrderService {
    /**
     * 新增订单
     *
     * @param dataSource
     * @param order
     */
    public Boolean addOrder(DataSource dataSource, Order order);

    /**
     * 根据Id获取订单
     *
     * @param dataSource
     * @param orderId
     * @return
     */
    public Order getOrdersById(DataSource dataSource, String orderId);
}
