package cz.upce.fei.inptp.zz.service.crypto;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CryptoFileServiceTest{



    @Before
    public void setUp() {
    }

    @Test
    public void encryptText() {
        String expected = "Test string";
        String password = "Password";
        CryptoFileService cryptoFileService = new CryptoFileService(null);
        String encrypted = cryptoFileService.encrypt(password, expected);
        assertNotEquals(expected, encrypted);
        String decrypted = cryptoFileService.decrypt(password, encrypted);
        assertEquals(expected, decrypted);
    }
}