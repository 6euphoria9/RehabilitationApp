package com.neurowvu.rehabilitationapp.dto;

import com.neurowvu.rehabilitationapp.entity.Doctor;
import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.security.SecurityUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class RegistrationPatientForm {

    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private Long doctorId;


    public SecurityUser toUser(PasswordEncoder passwordEncoder) {
        return new SecurityUser(
                new User());
    }
}
