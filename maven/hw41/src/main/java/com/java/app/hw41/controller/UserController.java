package com.java.app.hw41.controller;

import com.java.app.hw41.dto.UserReq;
import com.java.app.hw41.dto.UserWithPostReq;
import com.java.app.hw41.dto.UserWithPostRes;
import com.java.app.hw41.entity.Post;
import com.java.app.hw41.entity.User;
import com.java.app.hw41.service.PostService;
import com.java.app.hw41.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/")
    public List<User> getUsers(@RequestParam("email-domain") String emailDomain) {
        return userService.findUsersByEmailDomain(emailDomain);
    }

    @PostMapping("/")
    public Long createUser(@RequestBody UserReq userReq) {
        return userService.createUser(userReq.name(), userReq.email());
    }

    @PostMapping("/with-post")
    public UserWithPostRes createUserWithPost(@RequestBody UserWithPostReq userWithPostReq) {
        Long userId = userService.createUser(userWithPostReq.name(), userWithPostReq.email());
        UUID postId = postService.createPost(userWithPostReq.title(), userWithPostReq.content(), userService.findUserById(userId));
        return new UserWithPostRes(userId, postId);
    }
}
