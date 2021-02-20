package pers.peixinyi.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-02-18 13:55
 */
public class JdbcDemo {
    private static String INSERT_SQL = String.format("insert into student (student.id,student.username,student.age)values (%d,'%s',%d);", new Random().nextInt(1000), "裴欣奕", 1);
    private static String SELECT_SQL = "select * from student";
    private static String UPDATE_SQL = "update student set student.username = '裴欣X' where student.username='裴欣奕'";
    private static String DELTER_SQL = "DELETE FROM `week_time`.`student`";

    public static void main(String[] args) throws SQLException {
        JdbcUtil jdbcUtil = new JdbcUtil("jdbc:mysql://localhost:3306/week_time", "root", "123456");
        Connection connection = jdbcUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();
//        Integer rs = statement.executeUpdate(INSERT_SQL);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
        Integer rs = preparedStatement.executeUpdate();
        System.out.println("插入Sql" + INSERT_SQL + "共变更内容:" + rs);
        get(statement);
        rs = statement.executeUpdate(UPDATE_SQL);
        System.out.println("修改Sql" + UPDATE_SQL + "共变更内容:" + rs);
        get(statement);
        rs = statement.executeUpdate(DELTER_SQL);
        System.out.println("删除Sql" + DELTER_SQL + "共变更内容:" + rs);
        get(statement);
        //事物
        connection.setAutoCommit(false);
        try {
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            rs = preparedStatement.executeUpdate();
            System.out.println("插入Sql" + INSERT_SQL + "共变更内容:" + rs);
            int i = 1/0;
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            System.out.println("发生异常导致回滚插入失败");
        }
        JdbcUtil.closeJDBCResourceQuiet(connection, statement, null);
    }

    public static void get(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(SELECT_SQL);
        new Student().getStudent(resultSet);
    }
}
