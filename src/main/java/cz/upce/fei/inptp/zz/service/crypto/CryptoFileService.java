package cz.upce.fei.inptp.zz.service.crypto;

import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.service.json.JSONService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

/**
 * Service for creating encrypted files.
 *
 */
public class CryptoFileService implements CryptoService {

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
            // TODO...
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            CipherInputStream decipherInputStream = new CipherInputStream(fileInputStream, cipher);
            SecretKey secretKey = new SecretKeySpec(password.getBytes(), "DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            DataInputStream dataInputStream = new DataInputStream(decipherInputStream);
            String stringFromFile = dataInputStream.readUTF();
            dataInputStream.close();
            cipher.doFinal();
            
            return stringFromFile;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
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
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
            SecretKey secretKey = new SecretKeySpec(password.getBytes(), "DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            DataOutputStream dataOutputStream = new DataOutputStream(cipherOutputStream);
            dataOutputStream.writeUTF(textForWrite);
            dataOutputStream.close();
            cipher.doFinal();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(CryptoFileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
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
    public void writeFile(PasswordDatabase passwordDatabase) {
        writeFile(passwordDatabase.getFile(), passwordDatabase.getPasswd(), jsonService.toJson(passwordDatabase.getPasswords()));
    }
}
