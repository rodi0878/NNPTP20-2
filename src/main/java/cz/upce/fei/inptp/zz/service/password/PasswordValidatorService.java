/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.zz.service.password;

import cz.upce.fei.inptp.zz.entity.Password;

/**
 *
 * @author michc
 */
interface PasswordValidatorService {
    PasswordStrength getPasswordStrength(Password password);
}

enum PasswordStrength {
    STRONG,
    NORMAL,
    WEAK
}
