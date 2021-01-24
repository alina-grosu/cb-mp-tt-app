package cb_mp_tt_app.ciphering;

import javax.crypto.SecretKey;

/**
 * Represents Secret Key retrieval strategy
 */
public interface SecretKeyProvider {
    SecretKey getSecretKey();
}
