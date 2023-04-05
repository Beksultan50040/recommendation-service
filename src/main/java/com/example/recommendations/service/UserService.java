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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    private final UserDataRepository userDataRepository;

    @Autowired
    private EmailService emailService;

    @Value("${password.reset}")
    String link;


    public UserDto login(CredentialsDto credentialsDto) {
        UserCredentials userCredentials = userRepository.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), userCredentials.getPassword())) {
            return userMapper.toUserDto(userCredentials);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<UserCredentials> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserCredentials userCredentials = userMapper.signUpToUser(userDto);
        userCredentials.setPassword(Arrays.toString(userDto.getPassword().toCharArray()));
        userCredentials.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        UserCredentials savedUserCredentials = userRepository.save(userCredentials);


        userDataRepository.save(new UserData(userCredentials.getId(), userCredentials.getFirstName(), userCredentials.getLastName(),
                userCredentials.getEmail(), userCredentials.getGender(),
                userCredentials.getAge(), new ArrayList<>()));

        return userMapper.toUserDto(savedUserCredentials);
    }

    public UserDto findByLogin(String login) {
        UserCredentials userCredentials = userRepository.findByEmail(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(userCredentials);
    }

    public String resetPassword(String email){

        String body = """
            Пожалуйста используйте ссылку ниже для установки нового пароля, но сперва замените * на ваши данные
            потом скопируйте и вставьте в поисковике браузера и нажмите enter, готово
                    """ + "\n" + link;

        emailService.sendMail(email, body);
        return "Ok";
    }

    public String insertNewPassword(String password, String email){

        UserCredentials userCredentials = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        userCredentials.setPassword(passwordEncoder.encode(CharBuffer.wrap(password)));
        userRepository.save(userCredentials);

        return "Ok";
    }

}
