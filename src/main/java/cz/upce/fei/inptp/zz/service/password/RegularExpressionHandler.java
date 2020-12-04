/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.service.password;

/**
 *
 * @author michc
 */
public class RegularExpressionHandler {
    private static final String LOWER_CASE_CHARACTER_REGULAR_EXPRESSION = "(?=.*[a-z])";
    private static final String UPPER_CASE_CHARACTER_REGULAR_EXPRESSION = "(?=.*[A-Z])";
    private static final String SPECIAL_CHARACTERS_REGULAR_EXPRESSION = "(?=.*[~!@#$%^&*()_-]).*";
    private static final String NUMBERS_REGULAR_EXPRESSION = "(?=.*[0-9])";
    
    public static boolean HasLowerCaseCharacters(String text) {
        return text.matches(LOWER_CASE_CHARACTER_REGULAR_EXPRESSION);
    }
    
    public static boolean HasUpperCaseCharacters(String text) {
        return text.matches(UPPER_CASE_CHARACTER_REGULAR_EXPRESSION);
    }
    
    public static boolean HasNumbers(String text) {
        return text.matches(NUMBERS_REGULAR_EXPRESSION);
    }
    
    public static boolean HasSpecialCharacters(String text) {
        return text.matches(SPECIAL_CHARACTERS_REGULAR_EXPRESSION);
    }
}
