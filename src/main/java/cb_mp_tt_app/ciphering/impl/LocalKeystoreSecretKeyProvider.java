package cb_mp_tt_app.ciphering.impl;

import cb_mp_tt_app.ciphering.SecretKeyProvider;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.SecretKey;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * An implementation of {@link SecretKeyProvider} which uses a {@link KeyStore} obtained from a classpath.
 */
public class LocalKeystoreSecretKeyProvider implements SecretKeyProvider {

    private static final String KEYSTORE_TYPE = "JCEKS";
    private static final String ERROR_MESSAGE = "Unrecoverable error occured while retrieving the secret key!";
    /*perhaps should have been made configurable*/
    private static final String KEY_STORE_ALIAS = "cb-mp-tt-mk";
    private static final String KEY_STORE_NAME = "cb-mp-tt-ks.jks";
    /*perhaps there is some better way to pass key store password here*/
    private static final String KEY_STORE_PASSWORD = "cb-mp-tt-mpw";

    @Override
    public SecretKey getSecretKey() {
        try (InputStream keyStoreInputStream = new ClassPathResource(KEY_STORE_NAME).getInputStream()) {
            KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
            keyStore.load(keyStoreInputStream, KEY_STORE_PASSWORD.toCharArray());
            KeyStore.SecretKeyEntry secretKeyEntry
                    = (KeyStore.SecretKeyEntry) keyStore.getEntry(KEY_STORE_ALIAS, new KeyStore.PasswordProtection(KEY_STORE_PASSWORD.toCharArray()));
            return secretKeyEntry.getSecretKey();
        } catch (Exception e) {
            throw new RuntimeException(ERROR_MESSAGE, e);
        }
    }

}
