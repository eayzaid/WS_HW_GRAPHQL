package com.example.hw_graphql.controllers;

import com.example.hw_graphql.entities.Post;
import com.example.hw_graphql.entities.User;
import com.example.hw_graphql.repositories.PostRepository;
import com.example.hw_graphql.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @QueryMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<User> getUser(@Argument Long userId) {
        return userRepository.findById(userId);
    }

    @SchemaMapping(typeName = "User",field="posts")
    public List<Post> getPostsByUserId(User user){
        return postRepository.findByUserId(user.getId());
    }

    @MutationMapping
    public User createUser ( @Argument String username , @Argument String country , @Argument String email ,@Argument String tel  ){
        User newUser = new User();
        newUser.setCountry(country);
        newUser.setTel(tel);
        newUser.setEmail(email);
        newUser.setUsername(username);
        userRepository.save(newUser);
        return newUser;
    }

    @MutationMapping
    public User deleteUser( @Argument Long userId){
        User removedUser = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User is not found"));
        userRepository.deleteById(userId);
        return removedUser;
    }



}
