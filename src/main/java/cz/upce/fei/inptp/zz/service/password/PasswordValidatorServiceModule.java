/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.service.password;

import cz.upce.fei.inptp.zz.entity.Password;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author michc
 */
public class PasswordValidatorServiceModule implements PasswordValidatorService {
    private final Map<PasswordStrength, Integer> scoreLimits;

    public PasswordValidatorServiceModule() {
        scoreLimits = new HashMap<>();
        scoreLimits.put(PasswordStrength.NORMAL, 4);
        scoreLimits.put(PasswordStrength.STRONG, 6);
    }
    
    /**
     *
     * @param password The password to get the strength of
     * @return The strength of a password
     */
    @Override
    public PasswordStrength getPasswordStrength(Password password) {
        int score = getPasswordScore(password);
        
        if (score < scoreLimits.get(PasswordStrength.NORMAL)) {
            return PasswordStrength.WEAK;
        }
        else if (score >= scoreLimits.get(PasswordStrength.NORMAL) && score <= scoreLimits.get(PasswordStrength.STRONG)) {
            return PasswordStrength.NORMAL;
        }
        else {
            return PasswordStrength.STRONG;
        }
    }
    
    private int getPasswordScore(Password password) {
        int score = 0;
        String passswordToValidate = password.getPassword();
        
        if (passswordToValidate.length() < 6) {
            return 0;
        } else if (passswordToValidate.length() < 10) {
            score += 1;
        }
        else {
            score += 2;
        }
        
        if (RegularExpressionHandler.HasLowerCaseCharacters(passswordToValidate)) {
            score += 1;
        }
        
        if (RegularExpressionHandler.HasUpperCaseCharacters(passswordToValidate)) {
            score += 1;
        }
        
        if (RegularExpressionHandler.HasSpecialCharacters(passswordToValidate)) {
            score += 2;
        }
        
        if (RegularExpressionHandler.HasNumbers(passswordToValidate)) {
            score += 1;
        }
        
        return score;
    }
}
