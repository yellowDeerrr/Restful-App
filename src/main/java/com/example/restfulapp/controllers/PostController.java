package com.example.restfulapp.controllers;

import com.example.restfulapp.entity.Post;
import com.example.restfulapp.payload.request.post.CreatePostRequest;
import com.example.restfulapp.payload.request.post.EditPostMessageRequest;
import com.example.restfulapp.payload.request.post.EditPostNameRequest;
import com.example.restfulapp.payload.request.post.PostNameRequest;
import com.example.restfulapp.security.CustomUserDetails;
import com.example.restfulapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/get")
    public ResponseEntity<?> getPostByName(@RequestBody PostNameRequest postName){
        Post post = postService.getPostByPostName(postName.getPostName());
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.badRequest().body("No post named " + postName.getPostName());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequest createPostRequest, Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String res = postService.createPost(createPostRequest.getMessage(), createPostRequest.getPostName(), customUserDetails.getUsername());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }

    @PutMapping("/edit/post_message")
    public ResponseEntity<?> editPostMessage(@RequestBody EditPostMessageRequest editPostMessageRequest, Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String res = postService.editPostMessage(editPostMessageRequest.getNewMessage(), editPostMessageRequest.getPostName(), customUserDetails.getUsername());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }

    @PutMapping("/edit/post_name")
    public ResponseEntity<?> editPostName(@RequestBody EditPostNameRequest editPostNameRequest, Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String res = postService.editPostName(editPostNameRequest.getNewPostName(), editPostNameRequest.getPostName(), customUserDetails.getUsername());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestBody PostNameRequest postName, Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String res = postService.deletePost(customUserDetails.getUsername(), postName.getPostName());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }
}
