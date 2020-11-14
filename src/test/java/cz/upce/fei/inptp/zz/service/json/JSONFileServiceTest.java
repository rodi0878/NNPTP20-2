package cz.upce.fei.inptp.zz.service.json;

import cz.upce.fei.inptp.zz.entity.Category;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.entity.PasswordDatabaseTest;
import cz.upce.fei.inptp.zz.exception.JsonConversionException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JSONFileServiceTest {

    private JSONService jsonService = new JSONFileService();
    private String jsonFormatWithoutParameters;
    private String jsonFormatWithParameters;
    @Before
    public void setUp() {
        jsonFormatWithoutParameters = "[" +
                "{\"id\":0,\"password\":\"sdfghjkl\",\"parameters\":null,\"category\":null}," +
                "{\"id\":1,\"password\":\"ASDSAFafasdasdasdas\",\"parameters\":null,\"category\":null}," +
                "{\"id\":2,\"password\":\"aaa-aaaa-\",\"parameters\":null,\"category\":null}" +
                "]";
        jsonFormatWithParameters = "[" +
                "{\"id\":0,\"password\":\"sdfghjkl\"," +
                "   \"parameters\":null," +
                "   \"category\":null}," +
                "{\"id\":1,\"password\":\"ASDSAFafasdasdasdas\"," +
                "   \"parameters\":null," +
                "   \"category\":null}," +
                "{\"id\":2,\"password\":\"aaa-aaaa-\"," +
                "   \"parameters\":null," +
                "   \"category\":null}," +
                "{\"id\":3,\"password\":\"password1\",\"parameters\": [" +
                "   [\"website\",{\"value\":\"gmail.com\"} ]," +
                "   [\"description\",{\"value\":\"Password for my email\"}]," +
                "   [\"title\",{\"value\":\"email\"} ] ]," +
                "   \"category\":null}," +
                "{\"id\":4,\"password\":\"password4\",\"parameters\":[ " +
                "   [\"website\",{\"value\":\"tiktok.com\"}]," +
                "   [\"description\",{\"value\":\"Password for social media\"}]," +
                "   [\"title\",{\"value\":\"tiktok\"} ] ]," +
                "   \"category\":null } " +
                "]";
    }

    @Test
    public void toJson() throws JsonConversionException {
        String converted = jsonService.toJson(passwordsWithoutParameters());
        Assert.assertEquals(jsonFormatWithoutParameters, converted);
    }

    @Test
    public void fromJson() throws JsonConversionException {
        List<Password> convertedPasswords = jsonService.fromJson(jsonFormatWithoutParameters);
        Assert.assertEquals(passwordsWithoutParameters(), convertedPasswords);
    }

    @Test
    public void toJsonWithParameters() throws JsonConversionException {
        String converted = jsonService.toJson(passwordsWithParameters());
        Assert.assertEquals(jsonFormatWithParameters, converted);
    }

    @Test
    public void fromJsonWithParameters() throws JsonConversionException {
        List<Password> convertedPasswords = jsonService.fromJson(jsonFormatWithParameters);
        Assert.assertEquals(passwordsWithParameters(), convertedPasswords);
    }

    private List<Password> passwordsWithoutParameters(){
        List<Password>   passwords = new ArrayList<>();
        passwords.add(new Password(0, "sdfghjkl"));
        passwords.add(new Password(1, "ASDSAFafasdasdasdas"));
        passwords.add(new Password(2, "aaa-aaaa-"));
        return passwords;

    }


    private List<Password> passwordsWithParameters(){
        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(0, "sdfghjkl"));
        passwords.add(new Password(1, "ASDSAFafasdasdasdas"));
        passwords.add(new Password(2, "aaa-aaaa-"));
        Password emailPass = PasswordDatabaseTest.preparePassword(3, "password1", "email", "gmail.com", "Password for my email");
        Password tikTokPass = PasswordDatabaseTest.preparePassword(4, "password4", "tiktok", "tiktok.com", "Password for social media");
        passwords.add(emailPass);
        passwords.add(tikTokPass);
        return passwords;
    }
}
