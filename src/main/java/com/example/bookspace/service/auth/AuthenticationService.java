package com.example.bookspace.service.auth;

import com.example.bookspace.exception.ResourceAlreadyExistException;
import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.auth.User;
import com.example.bookspace.repository.auth.UserRepository;
import com.example.bookspace.utils.MyPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private MyPasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
        passwordEncoder = new MyPasswordEncoder();
    }

    public long register(User user) throws ResourceAlreadyExistException {
        if (checkDuplicateUser(user.getPhoneNumber(), user.getEmail()) == 200) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user).getUserId();
        } else throw new ResourceAlreadyExistException("");
    }

    public User authenticate(String username, String password) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findAll()
                .stream()
                .filter(user -> user.getPhoneNumber().equals(username) || user.getEmail().equals(username))
                .findFirst();
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("");
        } else {
            User user = optionalUser.get();
            if (user.getPassword().equals(passwordEncoder.encode(password))) {
                System.out.println(user.toString());
                return user;
            } else throw new ResourceNotFoundException("");
        }
    }

    public User updatePassword(String number, String newPassword) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findAll()
                .stream()
                .filter(user -> user.getPhoneNumber().equals(number))
                .findFirst();
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("");
        } else {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }
    }

    public User updateProfile(User updatedUser) throws ResourceNotFoundException {
        Optional<User> optionalUser = userRepository.findById(updatedUser.getUserId());
        if (!optionalUser.isPresent()) throw new ResourceNotFoundException("");
        else return userRepository.save(updatedUser);
    }

    public int checkDuplicateUser(String number, String email) {
        boolean anyMatch = userRepository.findAll()
                .stream()
                .anyMatch(user -> user.getPhoneNumber().equals(number)
                        || user.getEmail().equals(email));
        if (anyMatch) return 409;
        else return 200;
    }
}
