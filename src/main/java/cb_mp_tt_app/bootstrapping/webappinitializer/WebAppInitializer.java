package cb_mp_tt_app.bootstrapping.webappinitializer;


import cb_mp_tt_app.configurations.CipheringContextConfig;
import cb_mp_tt_app.configurations.EncryptionWebAppContextConfig;
import cb_mp_tt_app.configurations.RootAppContextConfig;
import cb_mp_tt_app.logging.CustomRequestLoggingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * This class gets called by Servlet 3.0 API complaint container while startup (e.g. Tomcat)
 * Replaces web.xml configuration
 * Registers Root and Encrypting Servlet contexts
 * Registers custom Filter in order to log requests and responses
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootAppContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{EncryptionWebAppContextConfig.class, CipheringContextConfig.class};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addFilter("customRequestLoggingFilter", new CustomRequestLoggingFilter())
                .addMappingForServletNames(null, false, getServletName());
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected String getServletName() {
        return "cb-mp-tt-app";
    }
}

