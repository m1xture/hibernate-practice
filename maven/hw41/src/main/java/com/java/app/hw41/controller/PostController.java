package com.java.app.hw41.controller;

import com.java.app.hw41.dto.PostReq;
import com.java.app.hw41.entity.Post;
import com.java.app.hw41.entity.User;
import com.java.app.hw41.service.PostService;
import com.java.app.hw41.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/")
    public UUID createPost(@RequestBody PostReq post) {
        User user = userService.findUserById(post.user_id());
        return postService.createPost(post.title(), post.content(), user);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable UUID id) {
        return postService.findById(id);
    }
}
