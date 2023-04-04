package com.example.recommendations.mapper;

import com.example.recommendations.dto.UserDataDto;
import com.example.recommendations.dto.UserMoviePreferenceDto;
import com.example.recommendations.entities.UserData;
import com.example.recommendations.entities.UserMoviePreference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDataMapper {


    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);



//    @Mapping(target = "id", expression = "java(userData.getId())")
//    @Mapping(target = "email", expression = "java(userData.getEmail())")
//    @Mapping(target = "firstName", expression = "java(userData.getFirstName())")
//    @Mapping(target = "lastName", expression = "java(userData.getLastName())")
//    @Mapping(target = "gender", expression = "java(userData.getGender())")
//    @Mapping(target = "age", expression = "java(userData.getAge())")
    @Mapping(target = "preferences", expression = "java(toDto(userData.getPreferences()))")
    UserDataDto toUserDataDto(UserData userData);

    default List<UserMoviePreferenceDto> toDto(List<UserMoviePreference> userMoviePreference) {

        List<UserMoviePreferenceDto> userMoviePreferenceDtos = new ArrayList<>();

        userMoviePreference.forEach(x -> {

            UserMoviePreferenceDto userMoviePreferenceDto = new UserMoviePreferenceDto();
            userMoviePreferenceDto.setMovie(x.getMovie());
            userMoviePreferenceDtos.add(userMoviePreferenceDto);
        });

        return userMoviePreferenceDtos;

    }


}
