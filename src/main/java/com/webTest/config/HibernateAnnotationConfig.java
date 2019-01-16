package com.webTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
@ComponentScan({ "com.webTest.models", "com.webTest.repositories", "com.webTest.services"})
public class HibernateAnnotationConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean  alertsSessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean ();
        sessionFactory.setDataSource( this.restDataSource() );
        sessionFactory.setPackagesToScan( new String[ ] { "com.webTest.models" } );
        sessionFactory.setHibernateProperties( this.hibernateProperties() );

        return sessionFactory;
    }
    @Bean
    public DataSource restDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClass"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }
    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory( this.alertsSessionFactory().getObject() );

        return txManager;
    }


    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
                setProperty("javax.persistence.validation.mode", "none");
            }
        };
    }
}