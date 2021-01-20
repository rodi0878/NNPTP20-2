package cz.upce.fei.inptp.zz.entity;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

import static cz.upce.fei.inptp.zz.entity.Parameter.StandardizedParameters;
import static cz.upce.fei.inptp.zz.entity.Parameter.TextParameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PasswordDatabaseTest {
    private PasswordDatabase database;

    @Before
    public void setUp() {
        database = new PasswordDatabase.PasswordDatabaseBuilder()
                .setFile(null)
                .setPassword("password0")
                .setPasswords(new ArrayList<>())
                .createPasswordDatabase();
    }

    @Test
    public void findEntryByTitleTest() {
        Password emailPass = preparePassword(1, "password1", "email", "gmail.com", "Password for my email");
        Password tikTokPass = preparePassword(2, "password4", "tiktok", "tiktok.com", "Password for social media");

        database.add(emailPass);
        database.add(tikTokPass);

        Password outputPassword1 = database.findEntryByTitle("email");
        assertEquals(emailPass, outputPassword1);

        Password outputPassword2 = database.findEntryByTitle("tiktok");
        assertEquals(tikTokPass, outputPassword2);
    }

    @Test
    public void findEntryByTitleNotFoundTest() {
        Password inputPassword = preparePassword(1, "password1", "email", "gmail.com", "Password for my email");
        database.add(inputPassword);

        Password outputPassword1 = database.findEntryByTitle("Key for e-mimino");
        assertNull(outputPassword1);
    }

    public static Password preparePassword(int id, String password, String tittle, String website, String description) {
        HashMap<String, Parameter> parameters = new HashMap<>();
        parameters.put(StandardizedParameters.TITLE, new TextParameter(tittle));
        parameters.put(StandardizedParameters.WEBSITE, new TextParameter(website));
        parameters.put(StandardizedParameters.DESCRIPTION, new TextParameter(description));
        parameters.put(StandardizedParameters.EXPIRATION_DATETIME, null);
        return new Password.PasswordBuilder()
                .setId(id)
                .setPassword(password)
                .setParameters(parameters)
                .createPassword();
    }

    @Test
    public void getPasswordByIdTest() throws InvalidParameterException {
        Password expectedPassword = new Password.PasswordBuilder()
                        .setId(2)
                        .setPassword("password3")
                        .createPassword();
        
        this.database.add(preparePassword(0, "password1", "email", "seznam.cz", "Password for my email"));
        this.database.add(preparePassword(1, "password2", "email", "gmail.com", "Password for my email"));
        this.database.add(preparePassword(2, "password3", "tiktok", "tiktok.com", "Password for social media"));

        Password password = this.database.getPasswordById(2);
        assertEquals(password, expectedPassword);
    }

    @Test
    public void editPasswordTest() {
        String expectedPassword = "Password";
        Password password = new Password.PasswordBuilder()
                        .setId(0)
                        .setPassword("paswd")
                        .createPassword();
        password.setPassword("Password");
        assertEquals(password.getPassword(), expectedPassword);
    }
}