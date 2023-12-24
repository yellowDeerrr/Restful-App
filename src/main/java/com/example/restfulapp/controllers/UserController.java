package com.example.restfulapp.controllers;

import com.example.restfulapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    static class EditUsernameRequest {
        private String username;
        private String password;
        private String newUsername;

        public EditUsernameRequest() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNewUsername() {
            return newUsername;
        }

        public void setNewUsername(String newUsername) {
            this.newUsername = newUsername;
        }
    }

    @PostMapping("/edit/username")
    public ResponseEntity<?> editUsername(@RequestBody EditUsernameRequest request) {
        String res = userService.editUsername(request.getUsername(), request.getPassword(), request.getNewUsername());
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }
    @PostMapping("/edit/password")
    public ResponseEntity<?> editPassword(@RequestBody String username, @RequestBody String password, @RequestBody String newPassword){
        String res = userService.editPassword(username, password, newPassword);
        return res.equals("Successful") ? ResponseEntity.ok(res) : ResponseEntity.badRequest().body(res);
    }
}
