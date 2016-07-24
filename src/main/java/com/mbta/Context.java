package com.mbta;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by moh on 7/23/16.
 */
@Configuration
public class Context {

    protected Logger log ;

    public Context() {
        log = LoggerFactory.getLogger(Context.class);
    }

    @Value("${db.host}")
    String dbHost ;

    @Value("${db.driver}")
    String dbDriver ;

    @Value("${db.user}")
    String dbUser ;

    @Value("${db.password}")
    String dbPassword ;

    @Bean
    @Scope("singleton")
    public DataSource getDataSource()throws Exception
    {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(dbDriver);
        ds.setUrl(dbHost);
        ds.setUsername(dbUser);
        ds.setPassword(dbPassword);
        ds.setMinIdle(1);
        ds.setMaxIdle(4);
        ds.setMaxOpenPreparedStatements(20);

        return ds;
    }

    @Bean
    @Scope("singleton")
    public JdbcTemplate getJdbcTemplate() throws Exception
    {

        JdbcTemplate ds = new JdbcTemplate(getDataSource());

        return ds;
    }
}
