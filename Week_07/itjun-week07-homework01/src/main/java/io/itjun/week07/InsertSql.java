package io.itjun.week07;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertSql {
    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil("jdbc:mysql://localhost:3306/geektime", "root", "");
        try {
            Connection connection = jdbcUtil.getConnect();
            connection.setAutoCommit(false);
            ReadSql readSql = new ReadSql("/Users/itjun/Desktop/million-200000.sql");
            List<String> sqlList = readSql.getSqlList();

            //2021年3月3日 16:02:02 - 添加多线程
            int threadNum = 4;

            if (sqlList.size() > Runtime.getRuntime().availableProcessors()) {
                threadNum = Runtime.getRuntime().availableProcessors();
            }
            CountDownLatch countDownLatch = new CountDownLatch(sqlList.size());
            System.out.println("CountDownLatch" + countDownLatch.getCount());
            ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

            Long start = System.currentTimeMillis();
            for (String sql : sqlList) {
                executorService.execute(new InsertThreadPool(sql, connection, countDownLatch));
            }
            countDownLatch.await();
            connection.commit();
            System.out.println("插入耗时:" + (System.currentTimeMillis() - start));
            connection.close();
            executorService.shutdown();
        } catch (SQLException | IOException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }
}
