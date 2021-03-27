package io.itjun.account.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-16 15:04
 */
@Configuration
public class DataSourceConfig {

    @Bean("db0")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db0")
    public DataSource db0() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("db0", db0());
        dataSourceMap.put("db1", db1());
        dynamicDataSource.setDefaultDataSource(db0());
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("pers.peixinyi.account");
        sessionFactory.setMapperLocations(new Resource[]{new ClassPathResource("mapper/FreezeMoneyMapper.xml")});

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
