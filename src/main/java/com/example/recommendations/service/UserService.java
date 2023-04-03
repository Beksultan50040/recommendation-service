package com.example.recommendations.service;


import com.example.recommendations.dto.CredentialsDto;
import com.example.recommendations.dto.SignUpDto;
import com.example.recommendations.dto.UserDto;
import com.example.recommendations.entities.UserCredentials;
import com.example.recommendations.entities.UserData;
import com.example.recommendations.exceptions.AppException;
import com.example.recommendations.mapper.UserMapper;
import com.example.recommendations.repository.UserDataRepository;
import com.example.recommendations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    private final UserDataRepository userDataRepository;


//    public UserDto login(CredentialsDto credentialsDto) {
//        UserCredentials userCredentials = userRepository.findByLogin(credentialsDto.getLogin())
//                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
//
//        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), userCredentials.getPassword())) {
//            return userMapper.toUserDto(userCredentials);
//        }
//        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
//    }

    public UserDto register(SignUpDto userDto) {
        Optional<UserCredentials> optionalUser = userRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserCredentials userCredentials = userMapper.signUpToUser(userDto);
        userCredentials.setPassword(Arrays.toString(userDto.getPassword()));
//        userCredentials.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        UserCredentials savedUserCredentials = userRepository.save(userCredentials);


        userDataRepository.save(new UserData(userCredentials.getId(), userCredentials.getFirstName(), userCredentials.getLastName(),
                userCredentials.getLogin(), new ArrayList<>()));

        return userMapper.toUserDto(savedUserCredentials);
    }

    public UserDto findByLogin(String login) {
        UserCredentials userCredentials = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(userCredentials);
    }

}
