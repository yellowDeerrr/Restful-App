package com.example.restfulapp.payload.request.post;

public class EditPostNameRequest {
    private String postName;
    private String newPostName;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getNewPostName() {
        return newPostName;
    }

    public void setNewPostName(String newPostName) {
        this.newPostName = newPostName;
    }
}
