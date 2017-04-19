package com.ninehcom.userinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by shixiaoqi on 2017/4/13.
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
