/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.upce.fei.inptp.zz.service.json.JSONFileCustomDeserializer;
import cz.upce.fei.inptp.zz.service.json.JSONFileCustomSerializer;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Roman
 */
public class Password {

    private int id;
    private String password;
    @JsonSerialize(using = JSONFileCustomSerializer.class)
    @JsonDeserialize(using = JSONFileCustomDeserializer.class)
    private HashMap<String, Parameter> parameters;
    private ICategory category;

    private Password(){

    }

    private Password(int id, String password, HashMap<String, Parameter> parameters) {
        this.id = id;
        this.password = password;
        this.parameters = parameters;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) { this.password = newPassword; }

    public HashMap<String, Parameter> getParameters() {
        return parameters;
    }

    public boolean hasParameter(String parameterName) {
        return parameters.containsKey(parameterName);
    }

    public Parameter getParameter(String parameterName) {
        return parameters.get(parameterName);
    }

    public ICategory getCategory() {
        return category;
    }

    public void setCategory(ICategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Password other = (Password) obj;
        return id == other.id
                && Objects.equals(password, other.password)
                && Objects.equals(category, other.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, category);
    }


    public static class PasswordBuilder {
        private int id;
        private String password;
        private HashMap<String, Parameter> parameters;

        public PasswordBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PasswordBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public PasswordBuilder setParameters(HashMap<String, Parameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Password createPassword() {
            return new Password(id, password, parameters);
        }
    }
}