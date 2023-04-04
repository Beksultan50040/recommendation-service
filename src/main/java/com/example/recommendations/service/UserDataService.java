package com.example.recommendations.service;


import com.example.recommendations.dto.UserDataDto;
import com.example.recommendations.entities.UserData;
import com.example.recommendations.mapper.UserDataMapper;
import com.example.recommendations.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {


    @Autowired
    private UserDataRepository userDataRepository;


    public UserDataDto getUserDataWithPreferences(String id){

        UserData userData = userDataRepository.findById(Long.valueOf(id)).orElse(new UserData());

        return UserDataMapper.INSTANCE.toUserDataDto(userData);

    }

}
