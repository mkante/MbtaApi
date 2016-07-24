package com.mbta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by moh on 7/23/16.
 */
@ComponentScan
@EnableAutoConfiguration(
        // Turn off Data source
        exclude={DataSourceAutoConfiguration.class}
)
public class App
{
    static Logger log = LoggerFactory.getLogger(App.class);

    public static void main (String ... args) {

        log.info ("Launching application.");
        SpringApplication.run(App.class, args);
    }
}
