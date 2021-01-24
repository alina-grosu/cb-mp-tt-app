package cb_mp_tt_app.ciphering.impl;

import cb_mp_tt_app.ciphering.CipheringStrategy;
import cb_mp_tt_app.ciphering.DecipheringStrategy;
import com.google.common.primitives.Bytes;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

import static javax.crypto.Cipher.ENCRYPT_MODE;

/**
 * Implementation of {@link CipheringStrategy} and {@link DecipheringStrategy}
 * which uses AES encryption algorithm for encryption and decryption.
 *
 */
public class AesCipheringDecipheringStrategy implements CipheringStrategy, DecipheringStrategy {
    
    private static final String AES_CBC_PKCS_5_PADDING = "AES/CBC/PKCS5Padding";
    private static final int INITIALIZATION_VECTOR_SIZE = 16;

    /**
     * @param unencrypted - text to be encrypted
     * @param secretKey - secret key to use for encryption
     * @return - encrypted data encoded to Base64 string
     *
     * This implementation generates new initialization vector for each call and
     * concatenates it to the end of the encrypted data array
     * in order to be able to decrypt later on (with same key)
     */
    @Override
    public String encrypt(String unencrypted, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);
            IvParameterSpec ivParameterSpec = generateInitializationVector();
            cipher.init(ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] cipheredText = cipher.doFinal(unencrypted.getBytes());

            /*append initialization vector to encrypted data in order to be able to obtain it while decrypting later on */
            byte[] cipheredAndIv = Bytes.concat(cipheredText, ivParameterSpec.getIV());
            return Base64.encodeBase64String(cipheredAndIv);
        } catch (Exception e) {
            throw new RuntimeException("Encryption process has failed!", e);
        }
    }

    /**
     * @param encrypted - encrypted data in the form of Base64 encoded string
     * @param secretKey - key to use for decryption
     * @return - decrypted data
     *
     * This implementation expects an initialization vector to be included at the end of encrypted data
     * for successful operation
     */
    @Override
    public String decrypt(String encrypted, SecretKey secretKey) {
        try {
            byte[] encryptedDataAndIv = Base64.decodeBase64(encrypted);

            /*extract initialization vector from encrypted input*/
            int ivIndex = encryptedDataAndIv.length - INITIALIZATION_VECTOR_SIZE;
            IvParameterSpec initializationVectorFromEncryptedInput
                    = new IvParameterSpec(encryptedDataAndIv, ivIndex, INITIALIZATION_VECTOR_SIZE);
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, initializationVectorFromEncryptedInput);

            /*decrypt only proper part of incoming byte array excluding iv bytes*/
            byte[] decrypted = cipher.doFinal(encryptedDataAndIv, 0, ivIndex);
            return new String(decrypted);
        } catch (Exception e) {
            throw new RuntimeException("Decryption process has failed!", e);
        }
    }

    private IvParameterSpec generateInitializationVector() {
        byte[] iv = new byte[INITIALIZATION_VECTOR_SIZE];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
