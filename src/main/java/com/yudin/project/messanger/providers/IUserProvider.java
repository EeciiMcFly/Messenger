package com.yudin.project.messanger.providers;
import com.yudin.project.messanger.dto.requests.RegisterUserRequest;
import com.yudin.project.messanger.objects.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IUserProvider {
    User getUserByNameAndPassword(String userName, String password);
    List<User> getUsersByName(String userName);
    void addUser(RegisterUserRequest registrationUserDTO);
    void removeUser(String userId);
}
