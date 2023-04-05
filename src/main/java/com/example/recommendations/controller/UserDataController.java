package com.example.recommendations.controller;


import com.example.recommendations.dto.UserDataDto;
import com.example.recommendations.service.UserDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @GetMapping()
    @Operation(summary = "ADMIN Endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserDataDto> getUserData(@RequestParam String userId){

        return ResponseEntity.ok(userDataService.getUserDataWithPreferences(userId));
    }

}
