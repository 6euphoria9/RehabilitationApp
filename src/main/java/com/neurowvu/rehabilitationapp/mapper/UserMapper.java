package com.neurowvu.rehabilitationapp.mapper;

import com.neurowvu.rehabilitationapp.dto.RegistrationPatientForm;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.dto.RegistrationDoctorForm;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapDoctorFormToUser(RegistrationDoctorForm form) {
        return new User()
                .setUsername(form.getUsername())
                .setPassword(form.getPassword())
                .setRole("ROLE_DOCTOR");
    }

    public User mapPatientFormToUser(RegistrationPatientForm form) {
        return new User()
                .setUsername(form.getUsername())
                .setPassword(form.getPassword())
                .setRole("ROLE_PATIENT");
    }
}
