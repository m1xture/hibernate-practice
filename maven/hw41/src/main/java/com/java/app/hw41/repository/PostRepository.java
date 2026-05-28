package com.java.app.hw41.repository;

import com.java.app.hw41.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthor_Id(Long authorId);
}
