package io.itjun.week07;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtil {
    private String jdbcUrl;
    private String username;
    private String password;

    DataSource getDataSource() {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(this.jdbcUrl);
        configuration.setUsername(this.username);
        configuration.setPassword(this.password);
        return new HikariDataSource(configuration);
    }

    public JdbcUtil(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getConnect() throws SQLException {
        return getDataSource().getConnection();
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
