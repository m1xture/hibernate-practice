package com.java.app.hw41.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@ToString(exclude = "posts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
//    private List<Post> posts;
}
