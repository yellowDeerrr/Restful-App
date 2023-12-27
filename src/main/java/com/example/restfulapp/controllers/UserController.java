package com.example.restfulapp.controllers;

import com.example.restfulapp.entity.UserEntity;
import com.example.restfulapp.payload.request.auth.LoginRequest;
import com.example.restfulapp.payload.request.user.delete.PasswordRequest;
import com.example.restfulapp.payload.request.user.edit.EditPasswordRequest;
import com.example.restfulapp.payload.request.user.edit.EditUsernameRequest;
import com.example.restfulapp.payload.response.LoginResponse;
import com.example.restfulapp.repositories.UserRepository;
import com.example.restfulapp.security.CustomUserDetails;
import com.example.restfulapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/you")
    public ResponseEntity<?> getUserData(Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new LoginResponse(customUserDetails.getId(),
                customUserDetails.getUsername(),
                customUserDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList())));
    }
    @PutMapping("/edit/username")
    public ResponseEntity<?> editUsername(@RequestBody EditUsernameRequest request) {
        String res = userService.editUsername(request.getUsername(), request.getPassword(), request.getNewUsername());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }
    @PutMapping("/edit/password")
    public ResponseEntity<?> editPassword(@RequestBody EditPasswordRequest request){
        String res = userService.editPassword(request.getUsername(), request.getPassword(), request.getNewPassword());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }

    @DeleteMapping("/delete/account")
    public ResponseEntity<?> deleteAccount(@RequestBody PasswordRequest password, Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String res = userService.deleteAccount(customUserDetails.getUsername(), password.getPassword());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }
}
