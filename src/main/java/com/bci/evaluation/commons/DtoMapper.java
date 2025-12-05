package com.bci.evaluation.commons;

import com.bci.evaluation.controller.dto.LoginResponseDto;
import com.bci.evaluation.controller.dto.PhoneDto;
import com.bci.evaluation.controller.dto.SignUpRequestDto;
import com.bci.evaluation.controller.dto.SignUpResponseDto;
import com.bci.evaluation.repository.Phone;
import com.bci.evaluation.repository.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class DtoMapper {

    private DtoMapper() {

    }

    public static User mapUserDtoToUser(SignUpRequestDto userDto) {

        User user = new User(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getName()
        );


        List<Phone> phones = userDto.getPhones()
                .stream()
                .map(phoneDto -> {
                    return mapPhoneDtoToPhone(phoneDto, user);
                })
                .collect(Collectors.toList());

        user.setPhones(phones);
        return user;
    }

    public static SignUpResponseDto mapUserToSignUpResponseDto(User user, String token) {
        return new SignUpResponseDto(
                user.getId(),
                user.getCreated(),
                user.getLastLogin(),
                token, user.isEnabled(),
                user.getUsername());
    }

    public static LoginResponseDto mapUserToLoginResponseDto(User user, String token) {

        List<PhoneDto> phoneDtos = user.getPhones()
                .stream()
                .map(DtoMapper::mapPhoneToPhoneDto)
                .collect(Collectors.toList());

        return new LoginResponseDto(
                user.getId().toString(),
                user.getCreated(),
                user.getLastLogin(),
                token,
                user.isEnabled(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                phoneDtos);
    }

    public static Phone mapPhoneDtoToPhone(PhoneDto phoneDto, User user) {

        return new Phone(user, phoneDto.getNumber(), phoneDto.getCitycode(), phoneDto.getCountrycode());
    }

    public static PhoneDto mapPhoneToPhoneDto(Phone phone) {
        return new PhoneDto(phone.getNumber(), phone.getCitycode(), phone.getCountrycode());
    }
}
