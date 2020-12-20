package cz.upce.fei.inptp.zz.service.password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service for secure password generation
 */
public class PasswordSecureGeneratorService implements PasswordGeneratorService {

    private static final SecureRandom random = new SecureRandom();
    private final List<Character> characterList = new ArrayList<>();

    /**
     * Initialization of PasswordSecureGeneratorService
     * Generates printable character sequence with random rotation
     */
    public PasswordSecureGeneratorService() {
        for (int i = 33; i < 127; i++) characterList.add((char) i);
        Collections.rotate(characterList, random.nextInt());
    }

    @Override
    public String getNewRandomPassword(int passwordLength) {
        StringBuilder password = new StringBuilder();
        for (int length = 0; length < passwordLength; length++) {
            password.append(getRandomCharacter());
        }
        return password.toString();
    }

    /**
     * Get one random character from the characters list
     * @return One random character
     */
    private char getRandomCharacter() {
        return characterList.get(random.nextInt(characterList.size()));
    }
}
