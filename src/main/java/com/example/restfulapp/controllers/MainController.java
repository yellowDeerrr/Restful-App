package com.example.restfulapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class MainController {
    @GetMapping("/")
    public String getMainPage(){
        return "Main Page";
    }

    @GetMapping("/api")
    public String getUserPage(){
        return "User Page";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "Admin Page";
    }
}
