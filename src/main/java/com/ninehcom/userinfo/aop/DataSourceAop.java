package com.ninehcom.userinfo.aop;

import com.ninehcom.userinfo.conf.DataSourceContextHolder;
import com.ninehcom.userinfo.controller.TestController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Logger;

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
        properties.load(DataSourceAop.class.getClassLoader().getResourceAsStream("test.properties"));
        for (int i = 0; i < paramNames.length; i++) {
            if ("appId".equals(paramNames[i])){
                String dasourceType=properties.getProperty(String.valueOf(objects[i]));
                log.info("dasourceType========="+dasourceType);
                DataSourceContextHolder.setDataSource(dasourceType);
//                if (objects[i].equals("3")){
//                    DataSourceContextHolder.setDataSource(DataSourceType.gaDataSource.getType());
//                }
//                if (objects[i].equals("13")){
//                    DataSourceContextHolder.setDataSource(DataSourceType.tdDataSource.getType());
//                }
//                if (objects[i].equals("1")){
//                    DataSourceContextHolder.setDataSource(DataSourceType.shDataSource.getType());
//                }
            }
        }
        return thisJoinPoint.proceed();
    }
}
