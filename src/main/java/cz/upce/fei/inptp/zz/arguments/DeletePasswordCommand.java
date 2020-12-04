package cz.upce.fei.inptp.zz.arguments;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Command arguments for deleting password from database.
 * */
@Parameters(commandDescription = "Delete password to database.")
public class DeletePasswordCommand extends CommandArguments {
    @Parameter(names = {"--id" ,"-i"}, required = true, description = "ID of password record.")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
