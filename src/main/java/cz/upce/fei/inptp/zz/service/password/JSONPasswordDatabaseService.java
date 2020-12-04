package cz.upce.fei.inptp.zz.service.password;

import com.google.inject.Inject;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.exception.JsonConversionException;
import cz.upce.fei.inptp.zz.service.crypto.CryptoService;
import cz.upce.fei.inptp.zz.service.json.JSONService;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Service for creating and reading password files using JSON.
 *
 * */
public class JSONPasswordDatabaseService implements PasswordDatabaseService {

    private Logger logger = Logger.getLogger(JSONPasswordDatabaseService.class.getName());

    private CryptoService cryptoService;

    private JSONService jsonService;

    @Inject
    public JSONPasswordDatabaseService(CryptoService cryptoService, JSONService jsonService) {
        this.cryptoService = cryptoService;
        this.jsonService = jsonService;
    }

    @Override
    public PasswordDatabase openPasswordDatabase(File path, String password) {
        String content = cryptoService.readFile(path, password);

        List<Password> passwordFromJSON = null;
        try {
            passwordFromJSON = jsonService.fromJson(content);
        } catch (JsonConversionException e) {
            logger.log(Level.SEVERE, "Error during converting JSON to Password list!", e);
        }
        return new PasswordDatabase(path, password, passwordFromJSON);
    }

    @Override
    public void savePasswordDatabase(PasswordDatabase passwordDatabase) {
        try {
            cryptoService.writeFile(passwordDatabase);
        } catch (JsonConversionException e) {
            logger.log(Level.SEVERE, "Error during converting Passwords list to JSON format. File with passwords is not saved!", e);
        }
    }
}
