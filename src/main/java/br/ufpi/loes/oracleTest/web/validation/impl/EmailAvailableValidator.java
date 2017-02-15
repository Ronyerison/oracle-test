/**
 * 
 */
package br.ufpi.loes.oracleTest.web.validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.ufpi.loes.oracleTest.web.model.User;
import br.ufpi.loes.oracleTest.web.repository.UserDao;
import br.ufpi.loes.oracleTest.web.validation.EmailAvailable;

public class EmailAvailableValidator implements ConstraintValidator<EmailAvailable, User> {
    
    @Inject
    private UserDao userDao;

    @Override
    public void initialize(EmailAvailable constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
    	return !userDao.containsUserWithEMail(user.getEmail());
    }
}
