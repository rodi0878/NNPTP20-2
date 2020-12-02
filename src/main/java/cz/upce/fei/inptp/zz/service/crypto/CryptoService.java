package cz.upce.fei.inptp.zz.service.crypto;

import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.exception.JsonConversionException;

import java.io.File;

/**
 * Service for creating encrypted files.
 *
 */
public interface CryptoService {
    /**
     * Read encrypted password file.
     *
     * @param file Encrypted file instance.
     * @param password Password to decrypt file.
     *
     * @return file content.
     *
     * */
    String readFile(File file, String password);

    /**
     * Write and encrypt content to file.
     *
     * @param file encrypted file location.
     * @param password password which will be used to encrypt file.
     * @param cnt file content.
     * */
    void writeFile(File file, String password, String cnt);

    /**
     * Encrypt content
     *
     * @param password password which will be used to encrypt file.
     * @param textToEncrypt text which will be encrypted
     * @return encrypted content
     * */
    String encrypt(String password, String textToEncrypt);

    /**
     * Decrypt content
     *
     * @param password password which will be used to encrypt file.
     * @param textToDecrypt text which will be decrypted
     * @return decrypted content
     * */
    String decrypt(String password, String textToDecrypt);

    /**
     * Write and encrypt the password database.
     *
     * @param passwordDatabase passwords database which will be saved to encrypted file.
     * */
    void writeFile(PasswordDatabase passwordDatabase) throws JsonConversionException;
}
