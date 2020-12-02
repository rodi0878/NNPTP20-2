package cz.upce.fei.inptp.zz.controller;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import cz.upce.fei.inptp.zz.arguments.AddPasswordCommand;
import cz.upce.fei.inptp.zz.arguments.DeletePasswordCommand;
import cz.upce.fei.inptp.zz.arguments.EditPasswordCommand;
import cz.upce.fei.inptp.zz.arguments.SelectPasswordsCommand;
import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.injector.InstanceInjector;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.service.password.PasswordDatabaseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
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
    private static final String ADD_COMMAND = "add";
    private static final String EDIT_COMMAND = "edit";
    private static final String DELETE_COMMAND = "delete";
    private static final String SELECT_COMMAND = "select";

    public static void main(String[] args) {
        AddPasswordCommand addCommand = InstanceInjector.injector().getInstance(AddPasswordCommand.class);
        EditPasswordCommand editCommand = InstanceInjector.injector().getInstance(EditPasswordCommand.class);
        DeletePasswordCommand deleteCommand = InstanceInjector.injector().getInstance(DeletePasswordCommand.class);
        SelectPasswordsCommand selectCommand = InstanceInjector.injector().getInstance(SelectPasswordsCommand.class);

        JCommander commandLineParser = JCommander.newBuilder()
                    .addCommand(ADD_COMMAND, addCommand)
                    .addCommand(EDIT_COMMAND, editCommand)
                    .addCommand(DELETE_COMMAND, deleteCommand)
                    .addCommand(SELECT_COMMAND, selectCommand)
                    .build();

        commandLineParser.parse(args);

        PasswordDatabaseService databaseService = InstanceInjector.injector().getInstance(PasswordDatabaseService.class);
        switch(commandLineParser.getParsedCommand()){
            case ADD_COMMAND:
                List<Password> pwds = Arrays.asList(new Password(0, addCommand.getPassword().getPassword()));
                databaseService.savePasswordDatabase(new PasswordDatabase(new File("test.txt"), "password", pwds));
                break;
            case SELECT_COMMAND:
                try {
                    String read = databaseService.openPasswordDatabase(new File("test.txt"), "password").getPassword();
                    System.out.println(read);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
