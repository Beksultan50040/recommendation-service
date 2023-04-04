package com.example.recommendations.controller;


import com.example.recommendations.config.UserAuthProvider;
import com.example.recommendations.dto.CredentialsDto;
import com.example.recommendations.dto.SignUpDto;
import com.example.recommendations.dto.UserDto;
import com.example.recommendations.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthenticationProvider;

    @PostMapping(consumes = "multipart/form-data", value = "/get")
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<String> healthCheck(@RequestParam MultipartFile file){

        return ResponseEntity.ok(file.getOriginalFilename());
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getEmail()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser.getEmail()));
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/reset")
    public String resetPassword( @RequestParam String email){

        return userService.resetPassword(email);

    }

    @GetMapping("/reset/password")
    public String insertNewPassword(@RequestParam String newPassword, @RequestParam String email){

        return userService.insertNewPassword(newPassword ,email);
    }

}