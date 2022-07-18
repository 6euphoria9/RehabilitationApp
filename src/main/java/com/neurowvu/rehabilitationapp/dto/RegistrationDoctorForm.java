package com.neurowvu.rehabilitationapp.dto;

import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationDoctorForm {

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;


    public SecurityUser toUser(PasswordEncoder passwordEncoder) {
        return new SecurityUser(
                new User());
    }
}
