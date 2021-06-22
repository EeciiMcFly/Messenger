package com.yudin.project.messanger.providers;

import com.yudin.project.messanger.database.DataReader;
import com.yudin.project.messanger.database.DataWriter;
import com.yudin.project.messanger.dto.requests.RegisterUserRequest;
import com.yudin.project.messanger.objects.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserProvider implements IUserProvider {
    private final DataReader dataReader;
    private final DataWriter dataWriter;

    public UserProvider(DataReader dataReader, DataWriter dataWriter) {
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }

    @Override
    public User getUserByName(String userName) {
        return dataReader.GetUserByName(userName);
    }

    @Override
    public List<User> getUsersByName(String userName) {
        return dataReader.GetUsersByName(userName);
    }

    @Override
    public User getUserById(String userId) {
        return dataReader.GetUserById(userId);
    }

    @Override
    public void addUser(RegisterUserRequest registrationUserDTO) {
        var newUserId = UUID.randomUUID().toString();
        var user = new User(newUserId, registrationUserDTO.getUserName(), registrationUserDTO.getPassword());

        dataWriter.AddUser(user);
    }

    @Override
    public void removeUser(String userId) {
        dataWriter.RemoveUser(userId);
    }
}
