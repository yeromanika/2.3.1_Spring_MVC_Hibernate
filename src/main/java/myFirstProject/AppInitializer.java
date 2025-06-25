package myFirstProject;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import myFirstProject.config.AppConfig;
import myFirstProject.config.HibernateConfig;
import myFirstProject.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {AppConfig.class, HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}