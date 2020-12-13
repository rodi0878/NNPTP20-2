package cz.upce.fei.inptp.zz.service.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import cz.upce.fei.inptp.zz.entity.Parameter;

import java.io.IOException;
import java.util.Map;

public class JSONFileCustomSerializer extends JsonSerializer<Map<String, Parameter>> {
    @Override
    public void serialize(final Map<String, Parameter> attrMap, final JsonGenerator generator, final SerializerProvider provider) {
        try {
            generator.writeStartArray();
            attrMap.entrySet().stream().forEach(e -> {
                        try {
                            generator.writeStartObject();
                            generator.writeFieldName(e.getKey());
                            generator.writeObject(e.getValue());
                            generator.writeEndObject();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
            );
            generator.writeEndArray();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}