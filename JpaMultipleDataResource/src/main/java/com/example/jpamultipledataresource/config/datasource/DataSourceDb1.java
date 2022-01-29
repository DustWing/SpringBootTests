package com.example.jpamultipledataresource.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.jpamultipledataresource.repositories.db1",
        entityManagerFactoryRef = "db1ManagerFactory",
        transactionManagerRef = "db1TransactionManager"
)
public class DataSourceDb1 {


    /**
     * Here it will get url, username, password and driver-class-name
     * which we have defined in application properties file for db1.
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }


    /**
     * Create the datasource using db1 DataSourceProperties
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.db1.configuration")
    public DataSource db1DataSource() {
        return db1DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }


    /**
     * EntityManager will find Entity classes inside the model package
     * (i.e com.example.jpamultipledataresource.dbModel.db1).
     *
     * @param builder
     * @return
     */
    @Bean(name = "db1ManagerFactory")
    public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(db1DataSource())
                .packages("com.example.jpamultipledataresource.dbModel.db1")
                .build();
    }

    @Bean
    public PlatformTransactionManager db1TransactionManager(
            final @Qualifier("db1ManagerFactory") LocalContainerEntityManagerFactoryBean db1EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(db1EntityManagerFactory.getObject()));
    }
}
