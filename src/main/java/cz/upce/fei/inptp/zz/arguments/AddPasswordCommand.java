package cz.upce.fei.inptp.zz.arguments;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import cz.upce.fei.inptp.zz.converter.PasswordParameterConverter;
import cz.upce.fei.inptp.zz.converter.TextParameterConverter;

/**
 * Command arguments for encrypt password to database.
 * */
@Parameters(commandDescription = "Add password to database.")
public class AddPasswordCommand extends CommandArguments {
    @Parameter(required = true, converter = PasswordParameterConverter.class,
            description = "Add password to to database.")
    private cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter password;

    @Parameter(names = {"--optional-parameter", "--parameter", "-o"}, converter = TextParameterConverter.class, variableArity = true,
            description = "Additional text parameters to save with passwords.")
    private List<cz.upce.fei.inptp.zz.entity.Parameter> optionalParameters = new ArrayList<>();

    public cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter getPassword() {
        return password;
    }

    public void setPassword(cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter password) {
        this.password = password;
    }

    public List<cz.upce.fei.inptp.zz.entity.Parameter> getOptionalParameters() {
        return optionalParameters;
    }

    public void setOptionalParameters(List<cz.upce.fei.inptp.zz.entity.Parameter> optionalParameters) {
        this.optionalParameters = optionalParameters;
    }
}
