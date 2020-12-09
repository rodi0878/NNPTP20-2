package cz.upce.fei.inptp.zz.converter;

import com.beust.jcommander.IStringConverter;
import cz.upce.fei.inptp.zz.entity.Parameter;

/**
 * Converter from text to @{@link Parameter.TextParameter}.
 *
 * */
public class TextParameterConverter implements IStringConverter<Parameter.TextParameter> {
    @Override
    public Parameter.TextParameter convert(String parameterValue) {
        return new Parameter.TextParameter(parameterValue);
    }
}
