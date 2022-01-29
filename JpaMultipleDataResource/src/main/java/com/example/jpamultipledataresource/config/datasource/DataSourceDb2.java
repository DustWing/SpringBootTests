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
        basePackages = "com.example.jpamultipledataresource.repositories.db2",
        entityManagerFactoryRef = "db2ManagerFactory",
        transactionManagerRef = "db2TransactionManager"
)
public class DataSourceDb2 {


    /**
     * Here it will get url, username, password and driver-class-name
     * which we have defined in application properties file for db2.
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }


    /**
     * Create the datasource using db2 DataSourceProperties
     *
     * @return
     */
    @Bean
    @ConfigurationProperties("spring.datasource.db2.configuration")
    public DataSource db2DataSource() {
        return db2DataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }


    /**
     * EntityManager will find Entity classes inside the model package
     * (i.e com.example.jpamultipledataresource.dbModel.db2).
     *
     * @param builder
     * @return
     */
    @Bean(name = "db2ManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(db2DataSource())
                .packages("com.example.jpamultipledataresource.dbModel.db2")
                .build();
    }

    @Bean
    public PlatformTransactionManager db2TransactionManager(
            final @Qualifier("db2ManagerFactory") LocalContainerEntityManagerFactoryBean db2EntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(db2EntityManagerFactory.getObject()));
    }
}
