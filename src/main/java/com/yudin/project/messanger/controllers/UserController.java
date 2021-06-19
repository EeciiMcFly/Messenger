package com.yudin.project.messanger.controllers;

import com.yudin.project.messanger.dto.*;
import com.yudin.project.messanger.dto.requests.AuthorizeRequest;
import com.yudin.project.messanger.dto.requests.DeleteUserRequest;
import com.yudin.project.messanger.dto.requests.FindUsersByNameRequest;
import com.yudin.project.messanger.dto.requests.RegisterUserRequest;
import com.yudin.project.messanger.enums.RegistrationStatus;
import com.yudin.project.messanger.providers.IUserProvider;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
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
        if (!StringUtils.hasText(findUsersByNameRequest.getUserName()))
        {
            return null;
        }

        var users = userProvider.getUsersByName(findUsersByNameRequest.getUserName());

        return users
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping
    public UserDTO Authorize(@RequestBody AuthorizeRequest authorizeRequest){
        if (!StringUtils.hasText(authorizeRequest.getLogin()) || !StringUtils.hasText(authorizeRequest.getPassword())){
            return null;
        }

        var user = userProvider.getUserByNameAndPassword(authorizeRequest.getLogin(), authorizeRequest.getPassword());

        if (user == null)
            return null;

        return new UserDTO(user);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterResult RegisterUser(@RequestBody RegisterUserRequest registrationUserDTO){
        if (!StringUtils.hasText(registrationUserDTO.getUserName()))
        {
            return new RegisterResult(RegistrationStatus.EmptyUserName);
        }

        if (!StringUtils.hasText(registrationUserDTO.getPassword()))
        {
            return new RegisterResult(RegistrationStatus.EmptyPassword);
        }

        var checkAlreadyUser = userProvider.getUserByNameAndPassword(registrationUserDTO.getUserName(), registrationUserDTO.getPassword());

        if (checkAlreadyUser != null)
        {
            return new RegisterResult(RegistrationStatus.UserAlreadyExits);
        }

        userProvider.addUser(registrationUserDTO);

        return new RegisterResult(RegistrationStatus.Success);
    }

    @DeleteMapping
    public void DeleteUser(@RequestBody DeleteUserRequest deleteUserRequest){
        if (!StringUtils.hasText(deleteUserRequest.getUserId()))
        {
            return;
        }

        userProvider.removeUser(deleteUserRequest.getUserId());
    }
}
