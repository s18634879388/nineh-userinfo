package com.ninehcom.userinfo.conf;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/11/11.
 */
public class DataSourceContextHolder {
    private static final Logger LOG = Logger.getLogger(DataSourceContextHolder.class.getName());
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }


    public static void setDataSource(Object obj) {
        local.set(String.valueOf(obj));
        LOG.info("正在设置数据源---------------------》"+String.valueOf(obj));
    }


    public static String getJdbcType() {
        return local.get();
    }
}
