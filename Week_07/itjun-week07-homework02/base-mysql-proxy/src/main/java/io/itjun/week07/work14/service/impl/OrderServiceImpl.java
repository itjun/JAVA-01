package io.itjun.week07.work14.service.impl;

import io.itjun.week07.work14.annotaion.Slave;
import io.itjun.week07.work14.entity.Order;
import io.itjun.week07.work14.service.OrderService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean addOrder(DataSource dataSource, Order order) {
        String sql = String.format("insert into orders (id,consumer_id)values('%s',%d);", order.getId(), order.getUserId());
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
    @Slave
    public Order getOrdersById(DataSource dataSource, String orderId) {
        Connection connection = null;
        String sql = "select * from orders where id = '" + orderId + "';";

        try {
            connection = dataSource.getConnection();
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            String id = "";
            while (resultSet.next()) {
                id = resultSet.getString("id");
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
