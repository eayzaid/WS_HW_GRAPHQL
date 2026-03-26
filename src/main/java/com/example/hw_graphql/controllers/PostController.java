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
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @QueryMapping
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @QueryMapping
    public Optional<Post> getPost(@Argument Long postId) {
        return postRepository.findById(postId);
    }

    @QueryMapping
    public List<Post> getPostsByUserId(@Argument Long userId){
        return postRepository.findByUserId(userId);
    }

    @SchemaMapping(typeName = "Post",field="user")
    public Optional<User> getUserByPost(Post post){
        return userRepository.findById(post.getUser().getId());
    }

    @MutationMapping
    public Post createPost ( @Argument Long userId , @Argument String content ){
        Post newPost = new Post();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found!"));;
        newPost.setContent(content);
        newPost.setUser(user);
        postRepository.save(newPost);
        return newPost;
    }

    @MutationMapping
    public Post deletePost( @Argument Long postId){
        Post removedPost = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post is not found"));
        postRepository.deleteById(postId);
        return removedPost;
    }

    @MutationMapping
    public Post updatePost ( @Argument Long postId , @Argument String newContent ){
        Post post = postRepository.findById(postId).orElseThrow(()-> new RuntimeException("Post is not found"));
        post.setContent(newContent);
        postRepository.save(post);
        return post;
    }
}
