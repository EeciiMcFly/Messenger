package com.yudin.project.messanger.dto;
import com.yudin.project.messanger.objects.User;

public class UserDTO{
    private String userId;
    private String login;

    public UserDTO(User user){
        this.userId = user.getUserId();
        this.login = user.getUserName();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }
}
