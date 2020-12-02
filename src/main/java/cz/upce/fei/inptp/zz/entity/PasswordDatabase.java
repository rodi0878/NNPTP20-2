/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import cz.upce.fei.inptp.zz.exception.IndexNotFoundException;

import java.io.File;
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

    public PasswordDatabase(File file, String password, List<Password> passwords) {
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

    public int getPasswordIndex(int passwordId) throws IndexNotFoundException {
        int index = -1;

        for (int i = 0; i < this.getPasswords().size(); i++) {
            if (this.getPasswords().get(i).getId() == passwordId) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return index;
        }
        throw new IndexNotFoundException("Password's index not found");
    }

    public void editPassword(Password oldPassword, String newStringPassword, int passwordIndex) {
        if (oldPassword != null && newStringPassword != null && passwordIndex >= 0) {
            oldPassword = new Password(oldPassword.getId(), newStringPassword, oldPassword.getParameters());
            this.getPasswords().set(passwordIndex, oldPassword);
        }
    }
}
