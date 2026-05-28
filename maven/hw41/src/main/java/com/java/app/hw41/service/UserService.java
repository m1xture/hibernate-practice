package com.java.app.hw41.service;

import com.java.app.hw41.entity.User;
import com.java.app.hw41.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findUsersByEmailDomain(String emailDomain) {
        return userRepository.findByEmailEndingWith(emailDomain);
    }

    public User findUserByName(String name) {
        return Optional.ofNullable(userRepository.findByName(name)).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public Long createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        var savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Transactional
    public void createUserWithRollback(String name, String email) {
        createUser(name, email);
        throw new RuntimeException("User rollback test!");
    }

}
