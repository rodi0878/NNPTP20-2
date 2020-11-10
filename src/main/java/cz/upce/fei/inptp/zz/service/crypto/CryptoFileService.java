package cz.upce.fei.inptp.zz.service.crypto;

import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.service.json.JSONService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

/**
 * Service for creating encrypted files.
 *
 */
public class CryptoFileService implements CryptoService {

    private static final String ENCRYPTION_ALGO = "AES/GCM/NoPadding";
    private static final String BASE_ALGO = "AES";

    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int SALT_LENGTH_BYTE = 16;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String KEY_CREATION_ALGORITHM = "PBKDF2WithHmacSHA256";

    private JSONService jsonService;

    @Inject
    public CryptoFileService(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public String readFile(File file, String password) {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            String stringFromFile = dataInputStream.readUTF();
            dataInputStream.close();
            
            return decrypt(password, stringFromFile);
        } catch (IOException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }
    
    @Override
    public void writeFile(File file, String password, String textForWrite) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(encrypt(password, textForWrite));
            dataOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String encrypt(String password, String textToEncrypt){
        try {
            byte[] salt = generateRandomNonce(SALT_LENGTH_BYTE);
            byte[] iv = generateRandomNonce(IV_LENGTH_BYTE);

            SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));

            byte[] cipherText = cipher.doFinal(textToEncrypt.getBytes(UTF_8));
            byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                    .put(iv)
                    .put(salt)
                    .put(cipherText)
                    .array();
            return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException |
                InvalidAlgorithmParameterException |
                InvalidKeySpecException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public String decrypt(String password, String textToDecrypt){
        try {
            byte[] decode = Base64.getDecoder().decode(textToDecrypt.getBytes(UTF_8));

            ByteBuffer bb = ByteBuffer.wrap(decode);
            byte[] iv = new byte[IV_LENGTH_BYTE];
            bb.get(iv);
            byte[] salt = new byte[SALT_LENGTH_BYTE];
            bb.get(salt);
            byte[] cipherText = new byte[bb.remaining()];
            bb.get(cipherText);

            SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
            byte[] plainText = cipher.doFinal(cipherText);
            return new String(plainText, UTF_8);
        }catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException |
                InvalidAlgorithmParameterException |
                InvalidKeySpecException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private byte[] generateRandomNonce(int length){
        byte[] nonce = new byte[length];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    private SecretKey getAESKeyFromPassword(char[] password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_CREATION_ALGORITHM);
        KeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), BASE_ALGO);
    }

    @Override
    public void writeFile(PasswordDatabase passwordDatabase) {
        writeFile(passwordDatabase.getFile(), passwordDatabase.getPasswd(), jsonService.toJson(passwordDatabase.getPasswords()));
    }
}
