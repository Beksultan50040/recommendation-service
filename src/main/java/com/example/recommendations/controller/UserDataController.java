package com.example.recommendations.controller;


import com.example.recommendations.dto.UserDataDto;
import com.example.recommendations.service.UserDataService;
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
    public ResponseEntity<UserDataDto> getUserData(@RequestParam String userId){

        return ResponseEntity.ok(userDataService.getUserDataWithPreferences(userId));
    }

}
