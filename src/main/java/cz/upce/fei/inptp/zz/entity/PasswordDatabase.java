/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Roman
 */
public class PasswordDatabase {
    private File file;
    private String password;
    
    private List<Password> passwords = new ArrayList<>();

    private PasswordDatabase(File file, String password, List<Password> passwords) {
        this.file = file;
        this.password = password;
        this.passwords = passwords;
    }

    public void add(Password password) {
        passwords.add(password);
    }
    
    public Password findEntryByTitle(String title) {
        return passwords
                .stream()
                .filter(password ->
                        password.hasParameter(Parameter.StandardizedParameters.TITLE)
                                && Objects.equals(((Parameter.TextParameter) password.getParameter(Parameter.StandardizedParameters.TITLE)).getValue(), title)
                )
                .findFirst()
                .orElse(null);
    }

    public File getFile() {
        return file;
    }

    public String getPassword() {
        return password;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public Password getPasswordById(int passwordId) throws InvalidParameterException {
        for (Password password : this.getPasswords()){
            if (password.getId() == passwordId){
                return password;
            }
        }
        throw new InvalidParameterException("Password ID not found");
    }

    public void editPassword(Password passwordToEdit, String newPassword) {
        if (passwordToEdit != null && newPassword != null){
            passwordToEdit.setPassword(newPassword);
        }          
    }

    public static class PasswordDatabaseBuilder {
        private File file;
        private String password;
        private List<Password> passwords;

        public PasswordDatabaseBuilder setFile(File file) {
            this.file = file;
            return this;
        }

        public PasswordDatabaseBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public PasswordDatabaseBuilder setPasswords(List<Password> passwords) {
            this.passwords = passwords;
            return this;
        }

        public PasswordDatabase createPasswordDatabase() {
            return new PasswordDatabase(file, password, passwords);
        }
    }
}
