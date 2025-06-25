package myFirstProject.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    // Настройка пула соединений HikariCP
    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/userdb?useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("qW3$rM6#pL9Z");
        ds.setAutoCommit(false);
        ds.setMaximumPoolSize(10);
        ds.setMinimumIdle(5);
        ds.setIdleTimeout(600000);
        ds.setConnectionTimeout(30000);
        return ds;
    }

    // Настройка EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("myFirstProject.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.connection.provider_disables_autocommit", "true");
        props.put("hibernate.connection.characterEncoding", "utf8");
        props.put("hibernate.connection.charSet", "utf8");
        props.put("hibernate.connection.useUnicode", "true");
        em.setJpaProperties(props);
        return em;
    }

    // Настройка менеджера транзакций
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}



