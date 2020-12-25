package cz.upce.fei.inptp.zz;

import com.beust.jcommander.JCommander;
import cz.upce.fei.inptp.zz.arguments.AddPasswordCommand;
import cz.upce.fei.inptp.zz.arguments.DeletePasswordCommand;
import cz.upce.fei.inptp.zz.arguments.EditPasswordCommand;
import cz.upce.fei.inptp.zz.arguments.SelectPasswordsCommand;
import cz.upce.fei.inptp.zz.entity.PasswordDatabase;
import cz.upce.fei.inptp.zz.injector.InstanceInjector;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.service.password.PasswordDatabaseService;
import cz.upce.fei.inptp.zz.service.password.PasswordSecureGeneratorService;
import cz.upce.fei.inptp.zz.service.password.PasswordGeneratorService;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
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
public class Main {
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
        PasswordGeneratorService passwordGenerator = InstanceInjector.injector().getInstance(PasswordSecureGeneratorService.class);
        String generatedPassword = passwordGenerator.getNewRandomPassword(20);
        PasswordDatabase passwordDatabase;

        switch(commandLineParser.getParsedCommand()){
            case ADD_COMMAND:
                Password password = new Password.PasswordBuilder()
                        .setId(0)
                        .setPassword(addCommand.getPassword().getPassword())
                        .createPassword();

                List<Password> pwds = Arrays.asList(password);

                passwordDatabase = new PasswordDatabase.PasswordDatabaseBuilder()
                        .setFile(addCommand.getPasswordFile())
                        .setPassword("password")
                        .setPasswords(pwds)
                        .createPasswordDatabase();

                databaseService.savePasswordDatabase(passwordDatabase);
                break;
            case SELECT_COMMAND:
                try {
                    String read = databaseService.openPasswordDatabase(selectCommand.getPasswordFile(), generatedPassword).getPassword();
                    System.out.println(read);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case EDIT_COMMAND:
                try {
                    passwordDatabase = databaseService.openPasswordDatabase(new File("test.txt"), generatedPassword);
                    Password passwordToEdit = passwordDatabase.getPasswordById(editCommand.getId());
                    passwordDatabase.editPassword(passwordToEdit, editCommand.getNewValue().getPassword());
                    databaseService.savePasswordDatabase(passwordDatabase);
                } catch (FileNotFoundException | InvalidParameterException e) {
                    e.printStackTrace();
                }
                break;
            case DELETE_COMMAND:
                try {
                    passwordDatabase = databaseService.openPasswordDatabase(new File("test.txt"), generatedPassword);
                    Password passwordToDelete = passwordDatabase.getPasswordById(editCommand.getId());
                    passwordDatabase.getPasswords().remove(passwordToDelete);
                    databaseService.savePasswordDatabase(passwordDatabase);
                } catch (FileNotFoundException | InvalidParameterException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
