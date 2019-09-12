package com.chnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = "com.chnu")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        Properties properties = new Properties();

        properties.put(DRIVER, Objects.requireNonNull(environment.getProperty("postgresql.driver")));
        properties.put(URL, Objects.requireNonNull(environment.getProperty("postgresql.url")));
        properties.put(USER, Objects.requireNonNull(environment.getProperty("postgresql.user")));
        properties.put(PASS, Objects.requireNonNull(environment.getProperty("postgresql.password")));

        properties.put(SHOW_SQL, Objects.requireNonNull(environment.getProperty("hibernate.show_sql")));
        properties.put(HBM2DDL_AUTO, Objects.requireNonNull(environment.getProperty("hibernate.hbm2ddl.auto")));
        properties.put(DIALECT, Objects.requireNonNull(environment.getProperty("hibernate.dialect")));

        properties.put(C3P0_MIN_SIZE, Objects.requireNonNull(environment.getProperty("hibernate.c3p0.min_size")));
        properties.put(C3P0_MAX_SIZE, Objects.requireNonNull(environment.getProperty("hibernate.c3p0.max_size")));
        properties.put(C3P0_ACQUIRE_INCREMENT, Objects.requireNonNull(environment.getProperty("hibernate.c3p0.acquire_increment")));
        properties.put(C3P0_TIMEOUT, Objects.requireNonNull(environment.getProperty("hibernate.c3p0.timeout")));
        properties.put(C3P0_MAX_STATEMENTS, Objects.requireNonNull(environment.getProperty("hibernate.c3p0.max_statements")));

        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setPackagesToScan("com.chnu.model");

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());

        return transactionManager;
    }
}
