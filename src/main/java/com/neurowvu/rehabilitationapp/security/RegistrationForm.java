package com.neurowvu.rehabilitationapp.security;

import com.neurowvu.rehabilitationapp.entity.PersonDetails;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String state;
    private String phoneNumber;

    public PersonDetails toUser(PasswordEncoder passwordEncoder) {
        return new PersonDetails(
                username, passwordEncoder.encode(password),
                fullname, address, state, phoneNumber);
    }
}
