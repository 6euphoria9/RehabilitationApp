package com.neurowvu.rehabilitationapp.services;

import com.neurowvu.rehabilitationapp.entity.User;
import com.neurowvu.rehabilitationapp.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
