package cb_mp_tt_app.ciphering;

import javax.crypto.SecretKey;

/**
 * A Deciphering Strategy
 */
public interface DecipheringStrategy {
    String decrypt(String encrypted, SecretKey secretKey);
}
