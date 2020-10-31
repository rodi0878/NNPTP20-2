/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.service.json;

import cz.upce.fei.inptp.zz.entity.Password;
import java.util.ArrayList;
import org.json.*;
import java.util.List;

/**
 * Service for creating JSON files.
 *
 * @author Roman
 *
 */
public class JSONFileService implements JSONService {

    @Override
    public String toJson(List<Password> passwords)  {
        // TODO: support all parameters!!!
        // TODO: support for categories - save them at once/save absolute path as array and later reconstruct?
        String output = "[";
        for (Password password : passwords) {
            if (!output.isEmpty() && !output.equals("["))
                output += ",";
            output += "{";
            output += "id:" + password.getId() + ",";
            output += "password:\"" + password.getPassword()+"\"";
            
            output += "}";
        }
        output += "]";
        
        return output;
    }
    
    @Override
    public List<Password> fromJson(String json) {
        List<Password> passwordsCollection = new ArrayList<>();
        
        JSONArray arrayOfPasswords = new JSONArray(json);
        for (int i = 0; i < arrayOfPasswords.length(); i++)
        {
            JSONObject object = arrayOfPasswords.getJSONObject(i);
            Object id = object.get("id");
            Object password = object.get("password");
            
            passwordsCollection.add(new Password((int) id, (String) password));
        }
        
        return passwordsCollection;
    }
}
