package com.evaitcsmatt.shoppinghub.console.repository;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.evaitcsmatt.shoppinghub.console.service.PeusdoShopServiceImpl;
import com.evaitcsmatt.shoppinghub.console.service.ShopService;
import com.evaitcsmatt.shoppinghub.console.utils.DataInitializer;

import jakarta.persistence.EntityManagerFactory;

/**
 * Spring configuration class for setting up JPA, data source, transaction management,
 * and application beans for the shopping hub application.
 */
@Configuration
@EnableJpaRepositories 
@EnableTransactionManagement
public class SimpleJpaConfig {
    /**
     * Creates the ShopService bean, depending on data initialization.
     * @param productRepository the product repository
     * @return the shop service implementation
     */
    @Bean
    @DependsOn("dataInitializer")
    public ShopService shopService(ProductRepository productRepository) {
        return new PeusdoShopServiceImpl(productRepository);
    }

    /**
     * Configures the application's data source for MySQL.
     * @return the data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shophubdb?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("Gudmord92!");
        return dataSource;
    }

    /**
     * Configures the JPA entity manager factory.
     * @return the entity manager factory bean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.evaitcsmatt.shoppinghub.console.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(hibernateProperties());

        return factory;
    }

    /**
     * Configures the JPA transaction manager.
     * @param emf the entity manager factory
     * @return the transaction manager
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    /**
     * Hibernate properties for JPA configuration.
     * @return the properties
     */
    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop"); //create, update, validate, none
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        return props;
    }

    /**
     * Creates the DataInitializer bean, depending on transaction manager.
     * @param productRepository the product repository
     * @return the data initializer
     */
    @Bean
    @DependsOn("transactionManager")
    public DataInitializer dataInitializer(ProductRepository productRepository) {
        return new DataInitializer(productRepository);
    }
}
