package com.yudin.project.messanger.dto.requests;

public class AddDialogRequest {
    private String firstUserId;
    private String secondUserId;

    public AddDialogRequest(String firstUserId, String secondUserId) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
    }

    public void setFirstUserId(String firstUserId) {
        this.firstUserId = firstUserId;
    }

    public void setSecondUserId(String secondUserId) {
        this.secondUserId = secondUserId;
    }

    public String getFirstUserId() {
        return firstUserId;
    }

    public String getSecondUserId() {
        return secondUserId;
    }
}
