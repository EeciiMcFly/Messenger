package com.yudin.project.messanger.controllers;

import com.yudin.project.messanger.dto.*;
import com.yudin.project.messanger.dto.requests.DeleteUserRequest;
import com.yudin.project.messanger.dto.requests.FindUserByIdRequest;
import com.yudin.project.messanger.dto.requests.FindUsersByNameRequest;
import com.yudin.project.messanger.dto.requests.RegisterUserRequest;
import com.yudin.project.messanger.enums.RegistrationStatus;
import com.yudin.project.messanger.providers.IUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserProvider userProvider;

    public UserController(IUserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @GetMapping("/findByUserName/{userName}")
    public List<UserDTO> GetUsersByName(@PathVariable("userName") String userName){
        if (!StringUtils.hasText(userName))
        {
            return null;
        }

        var users = userProvider.getUsersByName(userName);

        return users
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/findByUserId/{id}")
    public UserDTO GetUsersById(@PathVariable("id") String userId){
        if (!StringUtils.hasText(userId))
        {
            return null;
        }

        var user = userProvider.getUserById(userId);

        return new UserDTO(user);
    }

    @GetMapping
    public UserDTO Authorize(Authentication authentication){
        String userName = authentication.getName();
        var password = authentication.getCredentials();
        var user = userProvider.getUserByName(userName);

        if (user == null)
            return null;

        return new UserDTO(user);
    }

    @PostMapping("/register")
    public RegisterResult RegisterUser(@RequestBody RegisterUserRequest registrationUserDTO, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
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
