package com.example.restfulapp.service;

import com.example.restfulapp.entity.UserEntity;
import com.example.restfulapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;

    public String editUsername(String username, String password, String newUsername){
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent())

            if (passwordEncoder.matches(password, user.get().getPassword()))
                if (!userRepository.existsByUsername(newUsername)){

                    user.get().setUsername(newUsername);
                    userRepository.save(user.get());

                    return "Successful";
                }else
                    return "Username is already exist";
            else
                return "Password is not correct";
        else
            return "Username not found";
    }

    public String editPassword(String username, String password, String newPassword) {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (user.isPresent())

            if (passwordEncoder.matches(password, user.get().getPassword())){

                    user.get().setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user.get());

                    return "Successful";
            }else
                return "Password is not correct";
        else
            return "Username not found";
    }
}
