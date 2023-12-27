package com.example.restfulapp.payload.request.post;

import com.sun.istack.NotNull;

public class CreatePostRequest {
    @NotNull
    private String message;
    @NotNull
    private String postName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
