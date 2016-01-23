package cs309.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by jeffrey on 1/22/16.
 */
@Configuration
@EnableJpaRepositories("cs309.repo")
public class DataBaseConfig {
    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

//    @Bean
//    public SpringLiquibase liquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource());
//        return liquibase;
//    }
}
