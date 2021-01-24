package cb_mp_tt_app.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Root application context configuration, enables database connectivity features,
 * repositories, scans for JPA entities (replaces persistence.xml),
 * instantiates an H2 in memory database instance and populates it with sample data.
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "cb_mp_tt_app.db.repository")
@EntityScan("cb_mp_tt_app.db.model")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class RootAppContextConfig {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initializeDatabase() {
        Resource resource = new ClassPathResource("CreateUsersDatabase.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(dataSource);
    }

}

