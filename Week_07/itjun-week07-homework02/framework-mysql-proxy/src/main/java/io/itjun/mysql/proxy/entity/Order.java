package io.itjun.mysql.proxy.entity;

public class Order {
    private int id;
    private int userId;

    public Order() {
    }

    public Order(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                '}';
    }
}
