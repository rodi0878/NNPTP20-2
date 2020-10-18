/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.entity;

import java.util.HashMap;

/**
 *
 * @author Roman
 */
public class Password {

    private int id;
    private String password;
    private HashMap<ParameterType, Parameter> parameters;

    public Password() {
    }

    public Password(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public Password(int id, String password, HashMap<ParameterType, Parameter> parameters) {
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

    public HashMap<ParameterType, Parameter> getParameters() {
        return parameters;
    }

    boolean hasParameter(ParameterType parameterType) {
        return parameters.containsKey(parameterType);
    }

    public Parameter getParameter(ParameterType parameterType) {
        return parameters.get(parameterType);
    }
}
