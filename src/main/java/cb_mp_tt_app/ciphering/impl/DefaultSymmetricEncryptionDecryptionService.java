package cb_mp_tt_app.ciphering.impl;

import cb_mp_tt_app.ciphering.CipheringStrategy;
import cb_mp_tt_app.ciphering.DecipheringStrategy;
import cb_mp_tt_app.ciphering.EncryptionDecryptionService;
import cb_mp_tt_app.ciphering.SecretKeyProvider;

import javax.crypto.SecretKey;

/**
 * Default implementation of {@link EncryptionDecryptionService}
 * Obtains SecretKey only once while initializing and caches its instance for subsequent reuse.
 * e.g. this implementation always uses same key to encrypt/decrypt during app life.
 */
public class DefaultSymmetricEncryptionDecryptionService implements EncryptionDecryptionService {

    private final SecretKey secretKey;
    private CipheringStrategy cipheringStrategy;
    private DecipheringStrategy decipheringStrategy;

    public DefaultSymmetricEncryptionDecryptionService(SecretKeyProvider secretKeyProvider) {
        this.secretKey = secretKeyProvider.getSecretKey();
    }

    @Override
    public String encrypt(String unencryptedData) {
        return cipheringStrategy.encrypt(unencryptedData, secretKey);
    }

    @Override
    public String decrypt(String encryptedData) {
        return decipheringStrategy.decrypt(encryptedData, secretKey);
    }

    public CipheringStrategy getCipheringStrategy() {
        return cipheringStrategy;
    }

    public void setCipheringStrategy(CipheringStrategy cipheringStrategy) {
        this.cipheringStrategy = cipheringStrategy;
    }

    public DecipheringStrategy getDecipheringStrategy() {
        return decipheringStrategy;
    }

    public void setDecipheringStrategy(DecipheringStrategy decipheringStrategy) {
        this.decipheringStrategy = decipheringStrategy;
    }
}
