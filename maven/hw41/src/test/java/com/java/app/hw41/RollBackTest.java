package com.java.app.hw41;

import com.java.app.hw41.entity.User;
import com.java.app.hw41.repository.PostRepository;
import com.java.app.hw41.repository.UserRepository;
import com.java.app.hw41.service.PostService;
import com.java.app.hw41.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RollBackTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    void testUserRollback() {
        assertEquals(0, userRepository.count());

        assertThrows(RuntimeException.class, () ->
                userService.createUserWithRollback("John", "john@gmail.com")
        );

        assertEquals(0, userRepository.count()); // юзер не збережений
    }

    @Test
    void testPostRollback() {
        // Спочатку створюємо юзера без rollback
        Long userId = userService.createUser("John", "john@gmail.com");
        User author = userService.findUserById(userId);

        assertEquals(0, postRepository.count());

        assertThrows(RuntimeException.class, () ->
                postService.createPostWithRollback("Title", "Content", author)
        );

        assertEquals(0, postRepository.count()); // пост не збережений
        assertEquals(1, userRepository.count()); // юзер залишився
    }
}
