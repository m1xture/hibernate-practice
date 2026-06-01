package com.java.app.hw43.service;

import com.java.app.hw43.dto.UserRegistrationDto;
import com.java.app.hw43.model.User;
import com.java.app.hw43.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserRegistrationDto> getAllUsers() {
        return userRepository.findAll().stream().map(u -> {
            var regUser = new UserRegistrationDto();
            regUser.setUsername(u.getUsername());
            regUser.setPhone(u.getPhone());
            regUser.setEmail(u.getEmail());
            return regUser;
        }).toList();
    }

    private void checkUniqueFields(UserRegistrationDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }
        if (userRepository.existsByPhone(dto.getPhone())) {
            throw new IllegalArgumentException("Phone is already taken");
        }
    }

    public String register(UserRegistrationDto dto) {

        checkUniqueFields(dto);

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
        return user.getId();
    }

    public String save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }
}
