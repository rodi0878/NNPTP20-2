package cz.upce.fei.inptp.zz.service.crypto;

import cz.upce.fei.inptp.zz.exception.JsonConversionException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoFileServiceTest {

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

    @Test
    public void testWriteToFileANDReadFromFile() {
        File file = new File("test.txt");
        String text = "Hello world";
        String password = "FgS543059£Gsi%^&%$fDFS744gb7F-354g";
        CryptoFileService cryptoFileService = new CryptoFileService(null);
        cryptoFileService.writeFile(file, password, text);
        String result = cryptoFileService.readFile(file, password);
        assertEquals(text, result);
    }

    @Test
    public void testWriteToFile() throws JsonConversionException {
        CryptoFileService cryptoFileService = new CryptoFileService(null);
        CryptoFileService spyCryptoFileService = spy(cryptoFileService);
        doNothing().when(spyCryptoFileService).writeFile(any());
        spyCryptoFileService.writeFile(null);
        verify(spyCryptoFileService, times(1)).writeFile(any());
    }

}