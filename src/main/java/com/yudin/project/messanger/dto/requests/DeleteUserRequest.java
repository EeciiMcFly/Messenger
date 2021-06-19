package com.yudin.project.messanger.dto.requests;

public class DeleteUserRequest {
    private String userId;

    public DeleteUserRequest() {
    }

    public DeleteUserRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
