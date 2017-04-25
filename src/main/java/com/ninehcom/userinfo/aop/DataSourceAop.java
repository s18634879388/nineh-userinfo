package com.ninehcom.userinfo.aop;

import com.ninehcom.userinfo.conf.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by shixiaoqi on 2017/4/17.
 */

@Aspect
@Component
public class DataSourceAop {
    private static final Logger log = Logger.getLogger(DataSourceAop.class.getName());

    @Around("execution(* com.ninehcom.userinfo.controller..*.*(..))")
    public Object setWriteDataSourceType(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] objects = thisJoinPoint.getArgs();
        String[] paramNames = ((CodeSignature) thisJoinPoint.getStaticPart()
                .getSignature()).getParameterNames();
        Properties properties = new Properties();
        properties.load(DataSourceAop.class.getClassLoader().getResourceAsStream("appid-clubid.properties"));
        for (int i = 0; i < paramNames.length; i++) {
            if ("appId".equals(paramNames[i])){
                String dasourceType=properties.getProperty(String.valueOf(objects[i]));
                log.info("dasourceType========="+dasourceType);
                DataSourceContextHolder.setDataSource(dasourceType);
            }
        }
        return thisJoinPoint.proceed();
    }
}
