/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roman
 */
public class PasswordTest {
    private Password password;
    
    public PasswordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        password = new Password.PasswordBuilder()
                .setId(1)
                .setPassword("abcd%123")
                .createPassword();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEquals() {
        Password other = new Password.PasswordBuilder()
                .setId(1)
                .setPassword("abcd%123")
                .createPassword();
        assertEquals(password, other);

        other.setCategory(new Category.CategoryBuilder().createCategory());
        assertNotEquals(password, other);

        other = new Password.PasswordBuilder()
                .setId(2)
                .setPassword("abcd%123")
                .createPassword();
        assertNotEquals(password, other);

        other = new Password.PasswordBuilder()
                .setId(1)
                .setPassword("abcd%1234")
                .createPassword();
        assertNotEquals(password, other);
    }

    @Test
    public void setPasswordTest(){
        String expectedPassword = "Password";
        Password password = new Password.PasswordBuilder()
                        .setId(0)
                        .setPassword("paswd")
                        .createPassword();
        password.setPassword("Password");
        assertEquals(password.getPassword(), expectedPassword);
    }
}
