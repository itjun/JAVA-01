package io.itjun.mysql.proxy.service.impl;

import io.itjun.mysql.proxy.entity.Order;
import io.itjun.mysql.proxy.service.OrderService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean addOrder(DataSource dataSource, Order order) {
        String sql = String.format("insert into order (id,status)values(%d,%d);", order.getId(), order.getUserId());
        System.out.println(sql);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Order getOrdersById(DataSource dataSource, String orderId) {
        Connection connection = null;
        String sql = "select * from orders where id = '" + orderId + "';";

        try {
            connection = dataSource.getConnection();
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return new Order(id, 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
