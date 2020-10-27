package cz.upce.fei.inptp.zz.service.password;

import com.google.inject.Inject;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.service.crypto.CryptoService;
import cz.upce.fei.inptp.zz.service.json.JSONFileService;
import cz.upce.fei.inptp.zz.service.json.JSONService;

import java.io.File;
import java.util.List;

/**
 * Service for creating and reading password files using JSON.
 *
 * */
public class JSONPasswordDatabaseService implements PasswordDatabaseService {

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

        List<Password> passwordFromJSON = jsonService.fromJson(content);
        return new PasswordDatabase(path, password, passwordFromJSON);
    }

    @Override
    public void savePasswordDatabase(PasswordDatabase passwordDatabase) {
        cryptoService.writeFile(passwordDatabase);
    }
}
