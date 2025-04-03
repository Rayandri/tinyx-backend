package com.epita.service;

import com.epita.repository.UserRepository;
import com.epita.repository.entity.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;


@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.persist(user);
    }

    public List<User> getAllUsers() {
        return userRepository.listAll();
    }
    
}
