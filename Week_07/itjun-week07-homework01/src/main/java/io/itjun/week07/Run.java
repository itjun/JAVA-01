package io.itjun.week07;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Run {
    final private static int createNum = 1000000;
    final private static int createNumGroup = 100000;

    final static String sqlPath = "/Users/itjun/Desktop/million1-" + createNumGroup + ".sql";

    public static void main(String[] args) {
        File file = new File(sqlPath);
        try (OutputStream outputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);) {
            GenerateOrderSql generateOrderSql = new GenerateOrderSqlImpl();
            outputStreamWriter.write("insert into orders values ");
            for (int i = 0; i < createNum; i++) {
                outputStreamWriter.write(generateOrderSql.generate());
                if (i % createNumGroup != 0 && i != createNum - 1 || i == 0) {
                    outputStreamWriter.write(",");
                }
                if (i % createNumGroup == 0 && i != 0) {
                    outputStreamWriter.write(";\ninsert into orders values ");
                }
            }
            outputStreamWriter.write(";");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
