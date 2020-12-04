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
        password = new Password(1, "abcd%123");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEquals() {
        Password other = new Password(1, "abcd%123");
        assertEquals(password, other);

        other.setCategory(new Category());
        assertNotEquals(password, other);

        other = new Password(2, "abcd%123");
        assertNotEquals(password, other);

        other = new Password(1, "abcd%1234");
        assertNotEquals(password, other);
    }
    
}
