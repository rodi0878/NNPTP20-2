package cz.upce.fei.inptp.zz.arguments;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import cz.upce.fei.inptp.zz.converter.PasswordParameterConverter;

import java.io.File;

/**
 * Based command arguments for manipulating password files.
 * */
public abstract class CommandArguments {
    @Parameter(names = {"--file", "-f"}, required = true, converter = FileConverter.class,
            description = "Database file with encrypted passwords")
    protected File passwordFile;

    @Parameter(names = {"--file-password", "-p"}, required = true, password = true, echoInput = true,
            converter = PasswordParameterConverter.class,
            description = "File password to encrypt the file")
    protected cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter filePassword;

    public File getPasswordFile() {
        return passwordFile;
    }

    public void setPasswordFile(File passwordFile) {
        this.passwordFile = passwordFile;
    }

    public cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter getFilePassword() {
        return filePassword;
    }

    public void setFilePassword(cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter filePassword) {
        this.filePassword = filePassword;
    }
}
