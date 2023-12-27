package com.example.restfulapp.service;

import com.example.restfulapp.entity.Post;
import com.example.restfulapp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post getPostByPostName(String postName){
        return postRepository.findByPostName(postName).orElse(null);
    }

    public boolean checkPostName(String postName){
        return postRepository.existsByPostName(postName);
    }

    public boolean isOwnerOfPost(String postName, String username){
        return postRepository.existsByPostNameAndOwner(postName, username);
    }

    public Post getPostByPostNameAndUsername(String postName, String username){
        return postRepository.findByPostNameAndOwner(postName, username).orElse(null);
    }

    public String createPost(String message, String postName, String owner){
        if (!checkPostName(postName)){
            Post post = new Post();

            post.setPostName(postName);
            post.setMessage(message);
            post.setDate( new Timestamp(System.currentTimeMillis()));
            post.setOwner(owner);

            postRepository.save(post);
            return "Successful";
        }else
            return "Post name is already using";
    }

    public String editPostMessage(String newMessage, String postName, String owner) {
        Post post = getPostByPostNameAndUsername(postName, owner);
        if (post != null){
            post.setMessage(newMessage);
            postRepository.save(post);
            return "Successful";
        }else
            return "You are not owner of post or post name isn't correct";
    }

    public String editPostName(String newPostName, String postName, String owner) {
        Post post = getPostByPostNameAndUsername(postName, owner);
        if (post != null){
            post.setPostName(newPostName);
            postRepository.save(post);
            return "Successful";
        }else
            return "You are not owner of post or post name isn't correct";
    }

    public String deletePost(String username, String postName) {
        Post post = getPostByPostNameAndUsername(postName, username);
        if (post != null){
            postRepository.delete(post);
            return "Successful";
        }else
            return "You are not owner of post or post name isn't correct";
    }
}
