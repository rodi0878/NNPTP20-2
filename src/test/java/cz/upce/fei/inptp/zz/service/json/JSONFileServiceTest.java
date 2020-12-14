package cz.upce.fei.inptp.zz.service.json;

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
    private Password.PasswordBuilder builder = new Password.PasswordBuilder();
    private List<Password> examplePasswordsList;

    @Before
    public void setUp() {
        jsonFormatWithoutParameters = "[" +
                "{\"id\":0,\"password\":\"sdfghjkl\",\"parameters\":null,\"category\":null}," +
                "{\"id\":1,\"password\":\"ASDSAFafasdasdasdas\",\"parameters\":null,\"category\":null}," +
                "{\"id\":2,\"password\":\"aaa-aaaa-\",\"parameters\":null,\"category\":null}" +
                "]";
        jsonFormatWithParameters = "[" +
                "{\"id\":0,\"password\":\"sdfghjkl\",\"parameters\":null,\"category\":null}," +
                "{\"id\":1,\"password\":\"ASDSAFafasdasdasdas\",\"parameters\":null,\"category\":null}," +
                "{\"id\":2,\"password\":\"aaa-aaaa-\",\"parameters\":null,\"category\":null}," +
                "{\"id\":3,\"password\":\"password1\",\"parameters\":" +
                "[" +
                "{\"website\":{\"value\":\"gmail.com\"}}," +
                "{\"description\":{\"value\":\"Password for my email\"}}," +
                "{\"expiration-datetime\":null}," +
                "{\"title\":{\"value\":\"email\"}}" +
                "]," +
                "\"category\":null}," +
                "{\"id\":4,\"password\":\"password4\",\"parameters\":" +
                "[" +
                "{\"website\":{\"value\":\"tiktok.com\"}}," +
                "{\"description\":{\"value\":\"Password for social media\"}}," +
                "{\"expiration-datetime\":null}," +
                "{\"title\":{\"value\":\"tiktok\"}}" +
                "]," +
                "\"category\":null}" +
                "]";

        examplePasswordsList = new ArrayList<>();
        examplePasswordsList.add(builder
                .setId(0)
                .setPassword("sdfghjkl")
                .createPassword());
        examplePasswordsList.add(builder
                .setId(1)
                .setPassword("ASDSAFafasdasdasdas")
                .createPassword());
        examplePasswordsList.add(builder
                .setId(2)
                .setPassword("aaa-aaaa-")
                .createPassword());
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
        Assert.assertEquals(jsonFormatWithParameters.trim(), converted.trim());
    }

    @Test
    public void fromJsonWithParameters() throws JsonConversionException {
        List<Password> convertedPasswords = jsonService.fromJson(jsonFormatWithParameters);
        Assert.assertEquals(passwordsWithParameters(), convertedPasswords);
    }

    private List<Password> passwordsWithoutParameters() {
        List<Password> passwords = new ArrayList<>();
        passwords.add(builder
                .setId(0)
                .setPassword("sdfghjkl")
                .setParameters(null)
                .createPassword());
        passwords.add(builder
                .setId(1)
                .setPassword("ASDSAFafasdasdasdas")
                .setParameters(null)
                .createPassword());
        passwords.add(builder
                .setId(2)
                .setPassword("aaa-aaaa-")
                .setParameters(null)
                .createPassword());
        return passwords;

    }


    private List<Password> passwordsWithParameters() {
        List<Password> passwords = new ArrayList<>();
        passwords.add(builder
                .setId(0)
                .setPassword("sdfghjkl")
                .setParameters(null)
                .createPassword());
        passwords.add(builder
                .setId(1)
                .setPassword("ASDSAFafasdasdasdas")
                .setParameters(null)
                .createPassword());
        passwords.add(builder
                .setId(2)
                .setPassword("aaa-aaaa-")
                .setParameters(null)
                .createPassword());
        Password emailPass = PasswordDatabaseTest.preparePassword(3, "password1", "email", "gmail.com", "Password for my email");
        Password tikTokPass = PasswordDatabaseTest.preparePassword(4, "password4", "tiktok", "tiktok.com", "Password for social media");
        passwords.add(emailPass);
        passwords.add(tikTokPass);
        try {
            String json = jsonService.toJson(passwords);
            System.out.println(json);
            jsonService.fromJson(json);
        } catch (JsonConversionException e) {
            e.printStackTrace();
        }
        return passwords;
    }
}
