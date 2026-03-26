package com.example.hw_graphql.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String country;
    private String email;
    private String tel;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Post> posts;
}
