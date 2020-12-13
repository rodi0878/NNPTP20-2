/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.service.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.fei.inptp.zz.entity.Password;
import cz.upce.fei.inptp.zz.exception.JsonConversionException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

/**
 * Service for creating JSON files.
 *
 * @author Roman
 */
public class JSONFileService implements JSONService {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public String toJson(List<Password> passwords) throws JsonConversionException {
        StringBuilder result = new StringBuilder();
        result.append("[");
        try {
            for (Password password : passwords) {
                result.append(objectMapper.writeValueAsString(password));
                result.append(",");
            }
            result.deleteCharAt(result.length() - 1);
            result.append("]");
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Error during mapping Passwords to JSON.", e);
        }
        return result.toString();
    }

    @Override
    public List<Password> fromJson(String json) throws JsonConversionException {
        List<Password> passwords = null;
        try {
            passwords = objectMapper.readerFor(new TypeReference<List<Password>>() {
            }).readValue(json);
        } catch (JsonProcessingException e) {
            throw new JsonConversionException("Error during mapping Passwords to JSON.", e);
        }
        return passwords;
    }
}
