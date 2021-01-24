package cb_mp_tt_app.ciphering;

/**
 * Represents an encryption/decryption service
 */
public interface EncryptionDecryptionService {

    String encrypt(String unencryptedData);
    String decrypt(String encryptedData);

}
