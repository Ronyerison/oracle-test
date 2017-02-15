/**
 * 
 */
package br.ufpi.loes.oracleTest.web.validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.ufpi.loes.oracleTest.web.model.User;
import br.ufpi.loes.oracleTest.web.repository.UserDao;
import br.ufpi.loes.oracleTest.web.validation.LoginAvailable;

public class LoginAvailableValidator
    implements ConstraintValidator<LoginAvailable, User> {
    
    @Inject
    private UserDao userDao;

    @Override
    public void initialize(LoginAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return !userDao.containsUserWithLogin(user.getLogin());
    }
}
