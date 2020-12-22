package cz.upce.fei.inptp.zz.service.password;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordSecureGeneratorServiceTest {

    @Test
    public void getNewRandomPassword() {
        int passwordLength = 20;
        PasswordSecureGeneratorService passwordGenerator = new PasswordSecureGeneratorService();

        String firstPassword = passwordGenerator.getNewRandomPassword(passwordLength);
        String secondPassword = passwordGenerator.getNewRandomPassword(passwordLength);

        assertNotEquals(firstPassword, secondPassword);
    }
}
