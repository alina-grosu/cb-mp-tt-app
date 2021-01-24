package cb_mp_tt_app.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Default Web MVC configuration
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cb_mp_tt_app.rest")
public class EncryptionWebAppContextConfig {

}
