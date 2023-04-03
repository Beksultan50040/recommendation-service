package com.example.recommendations.controller;


import com.example.recommendations.config.UserAuthProvider;
import com.example.recommendations.dto.CredentialsDto;
import com.example.recommendations.dto.SignUpDto;
import com.example.recommendations.dto.UserDto;
import com.example.recommendations.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthenticationProvider;

    @GetMapping("/get")
    public String healthCheck(){

        return "Works";
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
//        UserDto userDto = userService.login(credentialsDto);
////        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
//        return ResponseEntity.ok(userDto);
//    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser.getLogin()));
        return ResponseEntity.ok(createdUser);
    }

}