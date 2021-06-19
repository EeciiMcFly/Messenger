package com.yudin.project.messanger.dto.requests;

public class FindUsersByNameRequest {
    private String userName;

    public FindUsersByNameRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
