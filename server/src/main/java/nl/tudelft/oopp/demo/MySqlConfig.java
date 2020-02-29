package nl.tudelft.oopp.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories
@PropertySource("application.properties")
@EnableTransactionManagement

public class MySqlConfig {

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://projects-db.ewi.tudelft.nl:3306/projects_OOPP-Project-29");
        dataSource.setUsername("pu_tx9MCiNb5ggcv");
        dataSource.setPassword("cmHmaXidf4R2");

        return dataSource;
    }
}


