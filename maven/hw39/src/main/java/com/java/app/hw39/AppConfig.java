package com.java.app.hw39;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.java.app.hw39")
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mysql://localhost:3306/homework39", "root", "root");
    }

    @Bean
    public JdbcOperations jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
