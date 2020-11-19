package cz.upce.fei.inptp.zz.controller;

import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.injector.InstanceInjector;
import cz.upce.fei.inptp.zz.service.json.JSONFileService;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.service.password.JSONPasswordDatabaseService;
import cz.upce.fei.inptp.zz.service.password.PasswordDatabaseService;
import cz.upce.fei.inptp.zz.service.password.PasswordSecureGeneratorService;
import cz.upce.fei.inptp.zz.service.password.PasswordGeneratorService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Enterprise password manager - in console
 * - uses strong industry-based encryption algorithm
 * - stores your passwords and relevent information
 * - allows you to simply manage all stored informations
 * 
 * 
 */
public class main {
    public static void main(String[] args) {
        List<Password> pwds = new ArrayList<>();
        pwds.add(new Password(0, "sdfghjkl"));
        pwds.add(new Password(1, "ASDSAFafasdasdasdas"));
        pwds.add(new Password(2, "aaa-aaaa-"));

        PasswordDatabaseService databaseService = InstanceInjector.injector().getInstance(PasswordDatabaseService.class);
        PasswordGeneratorService passwordGenerator = InstanceInjector.injector().getInstance(PasswordSecureGeneratorService.class);
        
        String password = passwordGenerator.getNewRandomPassword(20);
        
        
        databaseService.savePasswordDatabase(new PasswordDatabase(new File("test.txt"), password, pwds));

        try {
            String read = databaseService.openPasswordDatabase(new File("test.txt"), password).getPassword();
            System.out.println(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
   
}
