/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.password;

import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.service.password.PasswordValidatorServiceModule;
import cz.upce.fei.inptp.zz.service.password.PasswordValidatorServiceModule.PasswordStrength;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author michc
 */
public class PasswordValidatorServiceTest {
    @Test
    public void getPasswordStrength() {
        Password[] passwords = new Password[] {
            new Password(0, "mypass"),
            new Password(1, "MyPass"),
            new Password(2, "MyPassword123"),
            new Password(3, "0118999881990199725__3"),
            new Password(4, "MyStr0ngP4ssw0rd__")
        };
        
        PasswordValidatorServiceModule passwordValidator = new PasswordValidatorServiceModule();
        assertEquals(PasswordStrength.WEAK, passwordValidator.getPasswordStrength(passwords[0]));
        assertEquals(PasswordStrength.WEAK, passwordValidator.getPasswordStrength(passwords[1]));
        assertEquals(PasswordStrength.WEAK, passwordValidator.getPasswordStrength(passwords[2]));
        assertEquals(PasswordStrength.NORMAL, passwordValidator.getPasswordStrength(passwords[3]));
        assertEquals(PasswordStrength.STRONG, passwordValidator.getPasswordStrength(passwords[4]));
    }
}
