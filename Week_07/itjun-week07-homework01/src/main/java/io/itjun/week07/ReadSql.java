package io.itjun.week07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadSql {
    private String path;
    private List<String> sqlList = new ArrayList<>();

    public ReadSql(String path) throws IOException {
        this.path = path;
        this.readFile();
    }

    private void readFile() throws IOException {
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String sql;
        while ((sql = bufferedReader.readLine()) != null) {
            sqlList.add(sql);
        }
    }

    public List<String> getSqlList() {
        return sqlList;
    }
}
