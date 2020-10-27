package cz.upce.fei.inptp.zz.service.json;

import cz.upce.fei.inptp.zz.entity.Password;

import java.util.List;

/**
 * Service for creating and reading JSON files.
 *
 * */
public interface JSONService {
    /**
     * Create JSON format from passwords.
     *
     * @param passwords Passwords which will be convert to JSON.
     *
     * @return JSON formatted passwords.
     *
     * */
    String toJson(List<Password> passwords);

    /**
     * Convert passwords from JSON format.
     *
     * @param json JSON formatted passwords.
     *
     * @return passwords from JSON.
     *
     * */
    List<Password> fromJson(String json);
}
