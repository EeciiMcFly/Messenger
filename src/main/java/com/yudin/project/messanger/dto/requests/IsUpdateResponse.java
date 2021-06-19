package com.yudin.project.messanger.dto.requests;

public class IsUpdateResponse {
    public boolean existNewMessage;

    public IsUpdateResponse(boolean existNewMessage) {
        this.existNewMessage = existNewMessage;
    }
}
