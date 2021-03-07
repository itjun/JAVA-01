package io.itjun.week07.work14.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatasourceConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    Environment env;

    @Autowired
    SlaveDatasource slaveDatabase;

    @Primary
    @Bean(name = "master")
    public DataSource master() {
        log.info("loading database -> {}", env.getProperty("datasource.master.url"));
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl(env.getProperty("datasource.master.url"));
        configuration.setUsername(env.getProperty("datasource.master.username"));
        configuration.setPassword(env.getProperty("datasource.master.password"));
        return new HikariDataSource(configuration);
    }

    @Bean(name = "slaves")
    public List<DataSource> slaves() {
        List<DataSource> slaveDataSourceList = new ArrayList<>();
        for (BaseDataSourceAttribute dataSourceAttribute : slaveDatabase.slave) {
            log.info("loading database -> {}", dataSourceAttribute.getUrl());
            HikariConfig configuration = new HikariConfig();
            configuration.setJdbcUrl(dataSourceAttribute.getUrl());
            configuration.setUsername(dataSourceAttribute.getUsername());
            configuration.setPassword(dataSourceAttribute.getPassword());
        }
        return slaveDataSourceList;
    }

}
