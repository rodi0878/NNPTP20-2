package cz.upce.fei.inptp.zz.service.password;

public interface PasswordGeneratorService {

    /**
     * Get new random password of the required length
     *
     * @param passwordLength Sets the desired length of the random password
     * @return New random password of the required length
     */
    String getNewRandomPassword(int passwordLength);
}
