package io.itjun.week07.work14.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "datasource")
public class SlaveDatasource {

    List<BaseDataSourceAttribute> slave = new ArrayList<BaseDataSourceAttribute>();

    public SlaveDatasource() {
    }

    public SlaveDatasource(List<BaseDataSourceAttribute> slave) {
        this.slave = slave;
    }

    public List<BaseDataSourceAttribute> getSlave() {
        return slave;
    }

    public void setSlave(List<BaseDataSourceAttribute> slave) {
        this.slave = slave;
    }
}
