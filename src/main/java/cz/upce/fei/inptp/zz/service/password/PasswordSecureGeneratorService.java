package cz.upce.fei.inptp.zz.service.password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service for generate password
 */
public class PasswordSecureGeneratorService implements PasswordGeneratorService {

    private static SecureRandom random = new SecureRandom();
    private List<Character> characterList = new ArrayList<>();

    public PasswordSecureGeneratorService() {
        inicializationRandomPasswordGenerator();
    }

    @Override
    public String GetNewRandomPassword(int passwordLength) {
        StringBuilder password = new StringBuilder();

        for (int length = 0; length < passwordLength; length++) {
            password.append(getRandomCharacter());
        }

        return password.toString();
    }

    /**
     * Performs initialization of a random password generator
     */
    private void inicializationRandomPasswordGenerator() {

        generateSequenceOfCharacters(33, 53);
        generateSequenceOfCharacters(54, 85);
        generateSequenceOfCharacters(86, 127);

        Collections.rotate(characterList, random.nextInt());
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
        return this.characterList.get(random.nextInt(this.characterList.size()));
    }
}
