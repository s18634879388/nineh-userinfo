package com.ninehcom.userinfo.conf;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfig {

    @Bean(name = "getDataSources")
    public Map<Object,Object> getDataSources() throws PropertyVetoException, IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
//        Properties properties = new Properties();
//        properties.load(DataBaseConfig.class.getClassLoader().getResourceAsStream("appid-clubid.properties"));
//        Set<Object> set = properties.keySet();
//        DataBaseConfig dataBaseConfig = DataBaseConfig.class.newInstance();
//        for (Object obj:set
//                ) {
//            Method method = dataBaseConfig.getClass().getDeclaredMethod(String.valueOf(properties.get(obj)));
//            DataSource d = (DataSource) method.invoke(dataBaseConfig);
//            targetDataSources.put(properties.get(obj),d);
//        }
        targetDataSources.put(DataSourceType.gaDataSource.getType(),gaDataSource());
        targetDataSources.put(DataSourceType.tdDataSource.getType(),tdDataSource());
        targetDataSources.put(DataSourceType.shDataSource.getType(),shDataSource());
        return targetDataSources;
    }


    @Bean(name = "gaDataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.guoan_test")
    public DataSource gaDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "shDataSource")
    @ConfigurationProperties(prefix = "datasource.sh_test")
    public DataSource shDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "tdDataSource")
    @ConfigurationProperties(prefix = "datasource.td_test")
    public DataSource tdDataSource() {
        return DataSourceBuilder.create().build();
    }

}
