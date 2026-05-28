package com.java.app.hw41.service;

import com.java.app.hw41.entity.Post;
import com.java.app.hw41.entity.User;
import com.java.app.hw41.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public UUID createPost(String title, String content, User author) {
        var post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        return postRepository.save(post).getId();
    }

    public Post findById(UUID id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }


    public List<Post> findAllPostsByUser(Long userId) {
        return postRepository.findByAuthor_Id(userId);
    }

    @Transactional
    public void createPostWithRollback(String title, String content, User author) {
        createPost(title, content, author);
        throw new RuntimeException("Post rollback test!");
    }
}
