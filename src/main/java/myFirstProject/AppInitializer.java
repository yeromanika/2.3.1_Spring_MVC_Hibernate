package myFirstProject;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import myFirstProject.config.AppConfig;
import myFirstProject.config.HibernateConfig;
import myFirstProject.config.WebConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    public void onStartup(ServletContext aContext) throws ServletException {
        super.onStartup(aContext);

        FilterRegistration.Dynamic encodingFilter = aContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
    }
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