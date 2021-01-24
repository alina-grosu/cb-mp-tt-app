package cb_mp_tt_app.configurations;

import cb_mp_tt_app.ciphering.EncryptionDecryptionService;
import cb_mp_tt_app.ciphering.SecretKeyProvider;
import cb_mp_tt_app.ciphering.impl.AesCipheringDecipheringStrategy;
import cb_mp_tt_app.ciphering.impl.DefaultSymmetricEncryptionDecryptionService;
import cb_mp_tt_app.ciphering.impl.LocalKeystoreSecretKeyProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ciphering components configuration
 */
@Configuration
public class CipheringContextConfig {

    @Bean
    public SecretKeyProvider secretKeyProvider() {
        return new LocalKeystoreSecretKeyProvider();
    }

    @Bean
    public AesCipheringDecipheringStrategy aesCipheringDecipheringStrategy() {
        return new AesCipheringDecipheringStrategy();
    }

    @Bean
    public EncryptionDecryptionService encryptionDecryptionService() {
        DefaultSymmetricEncryptionDecryptionService service = new DefaultSymmetricEncryptionDecryptionService(secretKeyProvider());
        service.setCipheringStrategy(aesCipheringDecipheringStrategy());
        service.setDecipheringStrategy(aesCipheringDecipheringStrategy());
        return service;
    }

}
