package com.java.app.hw41.repository;

import com.java.app.hw41.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByName(String name);

    List<User> findByEmailEndingWith(String domain);
}
