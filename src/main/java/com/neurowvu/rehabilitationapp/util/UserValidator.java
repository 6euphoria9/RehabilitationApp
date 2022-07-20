package com.neurowvu.rehabilitationapp.util;


import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UsersRepository usersRepository;

    @Autowired
    public UserValidator(UsersRepository doctorsRepository) {
        this.usersRepository = doctorsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDoctorForm.class.equals(clazz) || RegistrationPatientForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target.getClass().equals(RegistrationDoctorForm.class)) {
            RegistrationDoctorForm user = (RegistrationDoctorForm) target;

            if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
                errors.rejectValue("username", "", "This username is already taken! Try to use another");
            }
        } else if (target.getClass().equals(RegistrationPatientForm.class)) {
            RegistrationPatientForm user = (RegistrationPatientForm) target;

            if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
                errors.rejectValue("username", "", "This username is already taken! Try to use another");
            }
        } else {
            throw new RuntimeException();
        }


    }
}
