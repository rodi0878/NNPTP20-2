/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Roman
 */
public class PasswordDatabase {
    private File file;
    private String passwd;

    private List<Password> passwords;

    public PasswordDatabase(File file, String passwd) {
        this.file = file;
        this.passwd = passwd;
    }

    public void load() {
        // TODO: use JSON and CryptoFile to load
        // TODO: throw exceptions when error
    }

    public void save() {
        // TODO: use JSON and CryptoFile t save
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
}
