package cb_mp_tt_app.ciphering.impl;

import cb_mp_tt_app.ciphering.SecretKeyProvider;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class AesCipheringDecipheringStrategyTest {

    private SecretKeyProvider secretKeyProvider;

    @Before
    public void setUp() {
        secretKeyProvider = new LocalKeystoreSecretKeyProvider();
    }

    @Test
    public void shouldEncryptAndDecryptSuccessfully() {
        //given
        String input = "kjasdhkjadhkqasjhwuieq qwuieyqiuey  qwueiyqeiyqwuiyeqiwuye 36163731323 1328632163 1731 3663";
        SecretKey secretKey = secretKeyProvider.getSecretKey();

        //when
        AesCipheringDecipheringStrategy strategy = new AesCipheringDecipheringStrategy();
        String encrypted = strategy.encrypt(input, secretKey);
        String decrypted = strategy.decrypt(encrypted, secretKey);
        //then
        assertEquals(input, decrypted);
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailDecryptionWithWrongKey() throws NoSuchAlgorithmException {
        //given
        String input = "kjasdhkjadhkqasjhwuieq qwuieyqiuey  qwueiyqeiyqwuiyeqiwuye 36163731323 1328632163 1731 3663";
        SecretKey secretKey = secretKeyProvider.getSecretKey();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey wrongSecretKey = keyGenerator.generateKey();

        //when
        AesCipheringDecipheringStrategy strategy = new AesCipheringDecipheringStrategy();
        strategy.decrypt(strategy.encrypt(input, secretKey), wrongSecretKey);

        //then
        //exception
    }

}