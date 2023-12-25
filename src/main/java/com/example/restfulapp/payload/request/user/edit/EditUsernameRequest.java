package com.example.restfulapp.payload.request.user.edit;

public class EditUsernameRequest {
    private String username;
    private String password;
    private String newUsername;

    public String getUsername () {
        return username;
    }

    public void setUsername (String username){
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getNewUsername () {
        return newUsername;
    }

    public void setNewUsername (String newUsername){
        this.newUsername = newUsername;
    }
}
