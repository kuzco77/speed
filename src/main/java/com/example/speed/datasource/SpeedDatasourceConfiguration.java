package com.example.speed.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.speed.repository"},
        entityManagerFactoryRef = "speedEntityManagerFactory",
        transactionManagerRef= "speedTransactionManager")

public class SpeedDatasourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        return new HikariConfig();
    }

    @Bean
    @Primary
    public DataSourceProperties speedDataSourceProperties() {
        DataSourceProperties properties = new DataSourceProperties();
        HikariConfig hikariConfig = new HikariConfig();
        properties.setDriverClassName(hikariConfig.getDriverClassName());
        properties.setUrl(hikariConfig.getJdbcUrl());
        properties.setUsername(hikariConfig.getUsername());
        properties.setPassword(hikariConfig.getPassword());
        return properties;
    }

    @Bean(name = "speedDataSource")
    @Primary
    public HikariDataSource speedDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Primary
    @Bean(name = "speedEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean speedEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(speedDataSource())
                .packages("com.example.speed.entity")
                .build();
    }

    @Primary
    @Bean(name = "speedTransactionManager")
    public PlatformTransactionManager speedTransactionManager(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(speedEntityManagerFactory(builder).getObject());
        tm.setDataSource(speedDataSource());
        return tm;
    }
}