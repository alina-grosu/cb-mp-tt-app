package cb_mp_tt_app.ciphering.impl;

import org.junit.Test;

import javax.crypto.SecretKey;

import static org.junit.Assert.*;

public class LocalKeystoreSecretKeyProviderTest {

    @Test
    public void shouldRetrieveSecretKeySuccessfully() {
        //when
        SecretKey secretKey = new LocalKeystoreSecretKeyProvider().getSecretKey();
        //then
        assertNotNull(secretKey);
    }

}