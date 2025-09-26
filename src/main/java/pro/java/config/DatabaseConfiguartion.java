package pro.java.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguartion {

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/java-pro");
        config.setSchema("public");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setMaximumPoolSize(5);
        return config;

    }

    @Bean
    public HikariPool hikariPool(HikariConfig config) {
        return new HikariPool(config);
    }
}
