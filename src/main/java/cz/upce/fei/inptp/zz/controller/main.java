package cz.upce.fei.inptp.zz.controller;

import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.injector.InstanceInjector;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.service.password.PasswordDatabaseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Enterprise password manager - in console - uses strong industry-based
 * encryption algorithm - stores your passwords and relevent information -
 * allows you to simply manage all stored informations
 *
 *
 */
public class main {

    public static void main(String[] args) {
        List<Password> passwordsList = new ArrayList<>();
        passwordsList.add(new Password(0, "sdfghjkl"));
        passwordsList.add(new Password(1, "ASDSAFafasdasdasdas"));
        passwordsList.add(new Password(2, "aaa-aaaa-"));

        PasswordDatabaseService databaseService = InstanceInjector.injector().getInstance(PasswordDatabaseService.class);
        databaseService.savePasswordDatabase(new PasswordDatabase(new File("test.txt"), "password", passwordsList));

        try {
            String read = databaseService.openPasswordDatabase(new File("test.txt"), "password").getPassword();
            System.out.println(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }
}
