package cz.upce.fei.inptp.zz.service.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cz.upce.fei.inptp.zz.entity.Parameter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class JSONFileCustomDeserializer extends JsonDeserializer<Map<String, Parameter>> {
    @Override
    public Map<String, Parameter> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Map<String, Parameter> deserializedMap = new HashMap<>();
        Parameter parameter = null;
        JsonNode jsonNode = jsonParser.readValueAsTree();

        if (jsonNode.isArray()) {
            for (JsonNode node: jsonNode) {
                String key = node.get(0).textValue();
                ObjectNode object = (ObjectNode) node.get(1);
            if (key.equals(Parameter.StandardizedParameters.TITLE) ||
                    key.equals(Parameter.StandardizedParameters.DESCRIPTION) ||
                            key.equals(Parameter.StandardizedParameters.WEBSITE)) {
                parameter = new Parameter.TextParameter(object.get("value").asText());
            }
            else if ( key.equals(Parameter.StandardizedParameters.EXPIRATION_DATETIME)) {
                parameter = new Parameter.DateTimeParameter(
                                LocalDateTime.parse(object.get("date-time").asText(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            }
            deserializedMap.put(key,parameter);
            }
        }
        return deserializedMap;
    }
}
