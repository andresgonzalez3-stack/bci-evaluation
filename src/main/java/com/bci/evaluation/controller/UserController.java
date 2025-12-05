package com.bci.evaluation.controller;

import com.bci.evaluation.controller.dto.LoginResponseDto;
import com.bci.evaluation.controller.dto.SignUpRequestDto;
import com.bci.evaluation.controller.dto.SignUpResponseDto;
import com.bci.evaluation.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto userDto) {
        SignUpResponseDto signUpResponseDto = usersService.saveUser(userDto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/login/{username}")
    public ResponseEntity<LoginResponseDto> login(@AuthenticationPrincipal Jwt jwt, @PathVariable("username") String username) {
        LoginResponseDto loginResponseDto = usersService.findUser(username);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.ACCEPTED);
    }

}
