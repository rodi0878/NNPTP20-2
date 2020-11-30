package cz.upce.fei.inptp.zz.arguments;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import cz.upce.fei.inptp.zz.converter.PasswordParameterConverter;
import cz.upce.fei.inptp.zz.converter.TextParameterConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Command arguments for editing password from database.
 * */
@Parameters(commandDescription = "Edit password in database.")
public class EditPasswordCommand extends CommandArguments {
    @Parameter(required = true, converter = PasswordParameterConverter.class,
            description = "Edit password value in database.")
    private cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter newValue;

    @Parameter(names = {"--id" ,"-i"}, required = true, description = "ID of password record.")
    private Integer id;

    @Parameter(names = {"--optional-parameter", "--parameter", "-o"}, converter = TextParameterConverter.class, variableArity = true,
            description = "Edit or add (if not exist) text parameters to save with passwords.")
    private List<cz.upce.fei.inptp.zz.entity.Parameter> optionalParameters = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter getNewValue() {
        return newValue;
    }

    public void setNewValue(cz.upce.fei.inptp.zz.entity.Parameter.PasswordParameter newValue) {
        this.newValue = newValue;
    }

    public List<cz.upce.fei.inptp.zz.entity.Parameter> getOptionalParameters() {
        return optionalParameters;
    }

    public void setOptionalParameters(List<cz.upce.fei.inptp.zz.entity.Parameter> optionalParameters) {
        this.optionalParameters = optionalParameters;
    }
}
