package ru.web.skyforce;

import lombok.SneakyThrows;
import org.apache.commons.dbcp.BasicDataSource;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.DriverManager;


@ComponentScan(basePackages = "ru.web.skyforce")
@EnableTransactionManagement
@Configuration
@PropertySource(value = "classpath:application.properties")
public class Appconfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    @Bean(name = "datasource")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean
    @SneakyThrows
    public CopyManager copyManager(){
           return new CopyManager((BaseConnection) DriverManager
                    .getConnection(url, username, password));
    }

}
