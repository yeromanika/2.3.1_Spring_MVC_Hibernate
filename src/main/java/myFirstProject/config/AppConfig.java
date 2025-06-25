package myFirstProject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("myFirstProject")
@Import({HibernateConfig.class, WebConfig.class})
public class AppConfig {
}
