/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Roman
 */
public class Password {

    private int id;
    private String password;
    private HashMap<String, Parameter> parameters;
    private ICategory category;

    public Password() {
    }

    public Password(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public Password(int id, String password, HashMap<String, Parameter> parameters) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;
        Password password1 = (Password) o;
        return id == password1.id &&
                Objects.equals(password, password1.password) &&
                Objects.equals(category, password1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, category);
    }
}
