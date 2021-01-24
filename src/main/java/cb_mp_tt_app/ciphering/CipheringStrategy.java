package cb_mp_tt_app.ciphering;

import javax.crypto.SecretKey;

/**
 * A Ciphering Strategy
 */
public interface CipheringStrategy {
    String encrypt(String unencrypted, SecretKey secretKey);
}
