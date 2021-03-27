package io.itjun.account.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-16 16:20
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.getDataSourceType();
    }

    public void setDefaultDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
    }
}
