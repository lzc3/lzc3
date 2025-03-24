package com.lzc.module.jdbc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StopWatch;

@SpringBootApplication
//@ComponentScan(value = "com.lzc.module.jdbc")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .logStartupInfo(false)
                .run(args);
        stopWatch.stop();
        logger.info("服务启动完成，耗时:{}s", stopWatch.getTotalTimeSeconds());
    }

}
