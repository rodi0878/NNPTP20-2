package cz.upce.fei.inptp.zz.converter;

import com.beust.jcommander.IStringConverter;
import cz.upce.fei.inptp.zz.entity.Parameter;

/**
 * Converter from text to @{@link Parameter.PasswordParameter}.
 *
 * */
public class PasswordParameterConverter implements IStringConverter<Parameter.PasswordParameter> {
    @Override
    public Parameter.PasswordParameter convert(String password) {
        return new Parameter.PasswordParameter(password);
    }
}
