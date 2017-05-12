package com.ninehcom.userinfo.conf;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import java.util.logging.Logger;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    private static final Logger LOG = Logger.getLogger(MyAbstractRoutingDataSource.class.getName());
//    private final int dataSourceNumber;
//    private AtomicInteger count = new AtomicInteger(0);

//    public MyAbstractRoutingDataSource(int dataSourceNumber) {
//        this.dataSourceNumber = dataSourceNumber;
//    }

    @Override
    protected Object determineCurrentLookupKey() {
        Object typeKey = DataSourceContextHolder.getJdbcType();
        //设置默认数据源
        if (typeKey==null){
            DataSourceContextHolder.setDataSource(DataSourceType.shDataSource);
            typeKey = DataSourceContextHolder.getJdbcType();
        }
        LOG.info("数据源切换到----->"+typeKey);
        return typeKey;
    }
}
