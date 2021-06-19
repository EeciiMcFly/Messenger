package com.yudin.project.messanger.controllers;

import com.yudin.project.messanger.dto.*;
import com.yudin.project.messanger.dto.requests.DeleteUserRequest;
import com.yudin.project.messanger.dto.requests.FindUsersByNameRequest;
import com.yudin.project.messanger.dto.requests.RegisterUserRequest;
import com.yudin.project.messanger.enums.RegistrationStatus;
import com.yudin.project.messanger.providers.IUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserProvider userProvider;

    public UserController(IUserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @GetMapping("/findByUserName")
    public List<UserDTO> GetUsersByName(@RequestBody FindUsersByNameRequest findUsersByNameRequest){
        if (!StringUtils.hasText(findUsersByNameRequest.userName))
        {
            return null;
        }

        var users = userProvider.getUsersByName(findUsersByNameRequest.userName);

        return users
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping
    public UserDTO Authorize(Authentication authentication){
        String userName = authentication.getName();
        var user = userProvider.getUserByName(userName);

        if (user == null)
            return null;

        return new UserDTO(user);
    }

    @PostMapping("/register")
    public RegisterResult RegisterUser(@RequestBody RegisterUserRequest registrationUserDTO){
        if (!StringUtils.hasText(registrationUserDTO.getUserName()))
        {
            return new RegisterResult(RegistrationStatus.EmptyUserName);
        }

        if (!StringUtils.hasText(registrationUserDTO.getPassword()))
        {
            return new RegisterResult(RegistrationStatus.EmptyPassword);
        }

        var checkAlreadyUser = userProvider.getUserByName(registrationUserDTO.getUserName());

        if (checkAlreadyUser != null)
        {
            return new RegisterResult(RegistrationStatus.UserAlreadyExits);
        }

        userProvider.addUser(registrationUserDTO);

        return new RegisterResult(RegistrationStatus.Success);
    }

    @DeleteMapping
    public void DeleteUser(@RequestBody DeleteUserRequest deleteUserRequest){
        if (!StringUtils.hasText(deleteUserRequest.userId))
        {
            return;
        }

        userProvider.removeUser(deleteUserRequest.userId);
    }
}
