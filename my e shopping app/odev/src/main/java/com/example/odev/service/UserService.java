package com.example.odev.service;

import com.example.odev.Model.User;
import com.example.odev.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void registerNewUser(String username, String email, String firstName, String lastName, LocalDate birthDate, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setPassword(password); // Şifre kaydediliyor
        userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return password.equals(user.getPassword()); // Şifre kontrolü direkt olarak yapılıyor
        }
        return false;
    }
}
