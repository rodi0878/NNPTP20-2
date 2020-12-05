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
    private static final List<Character> characterList = new ArrayList<>();

    /**
     * Initialization of PasswordSecureGeneratorService
     * Generates printable character sequence with random trim and random rotation
     */
    public PasswordSecureGeneratorService() {
        generateSequenceOfCharacters(33+random.nextInt(5), 127-random.nextInt(5));
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
     * Generate a sequence of characters from a specified interval
     *
     * @param min The minimum value from which generates a sequence.
     * @param max The maximum value to which the sequence is generated.
     */
    private void generateSequenceOfCharacters(int min, int max) {
        for (int i = min; i < max; i++) {
            characterList.add((char) i);
        }
    }

    /**
     * Get one random character from the characters list
     * @return One random character
     */
    private char getRandomCharacter() {
        return characterList.get(random.nextInt(characterList.size()));
    }
}
