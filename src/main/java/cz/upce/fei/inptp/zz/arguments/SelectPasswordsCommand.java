package cz.upce.fei.inptp.zz.arguments;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameters;

import java.util.HashMap;
import java.util.Map;

/**
 * Command arguments for selecting passwords from database.
 * */
@Parameters(commandDescription = "Find passwords by optional parameters.")
public class SelectPasswordsCommand extends CommandArguments {
    @DynamicParameter(names = "-F", description = "Find by attribute name and value.")
    private Map<String, String> params = new HashMap<>();

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
