package cz.upce.fei.inptp.zz.service.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import cz.upce.fei.inptp.zz.entity.Parameter;

import java.io.IOException;
import java.util.Map;

public class JSONFileCustomSerializer extends JsonSerializer<Map<String, Parameter>> {
    @Override
    public void serialize(final Map<String, Parameter> attrMap, final JsonGenerator generator, final SerializerProvider provider)
            throws IOException {
        generator.writeStartArray();
        for (Map.Entry<String, Parameter> entry : attrMap.entrySet()){
            generator.writeStartArray();
            generator.writeString(entry.getKey());
            generator.writeObject(entry.getValue());
            generator.writeEndArray();
        }
        generator.writeEndArray();
    }
}