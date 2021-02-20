package io.itjun.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class JdbcUtil {
    public static void main(String[] args) throws SQLException {
        JdbcUtil jdbcUtil = new JdbcUtil("jdbc:mysql://localhost:3306/week_time", "root", "123456");
        Connection connection = jdbcUtil.connect();
        String sql = "select * from student";
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getMetaData().getColumnName(1));
        }
    }

    private String url;
    private String username;
    private String password;

    public JdbcUtil(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * <p> 获取DataSource使用Hikari </p>
     *
     * @return javax.sql.DataSource
     * @author PeiXy
     * @version 1.0.0.0
     * @since 15:00 2021/2/18
     */
    public DataSource getHikariDataSource() {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(this.url);
        configuration.setUsername(this.username);
        configuration.setPassword(this.password);
        return new HikariDataSource(configuration);
    }

    /**
     * <p> 建立连接 </p>
     *
     * @return java.sql.Connection
     * @author PeiXy
     * @version 1.0.0.0
     * @since 15:01 2021/2/18
     */
    private Connection connect() throws SQLException {
        return getHikariDataSource().getConnection();
    }

    /**
     * <p> 回滚 </p>
     *
     * @param connection
     * @return void
     * @author PeiXy
     * @version 1.0.0.0
     * @since 15:01 2021/2/18
     */
    public void rollback(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeJDBCResourceQuiet(Connection connection, Statement preparedStatement, ResultSet resultSet) {
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(preparedStatement)) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
