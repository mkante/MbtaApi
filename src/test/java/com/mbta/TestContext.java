package com.mbta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created by moh on 7/23/16.
 */
@Configuration
@Import(Context.class)
@EnableAutoConfiguration
@Profile("test")
public class TestContext
{

}
