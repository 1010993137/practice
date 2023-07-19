package com.houfz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author houfz
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class PracticeApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(PracticeApplication.class, args);
        System.out.println("启动成功");
    }
}
