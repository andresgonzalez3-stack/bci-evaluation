package com.bci.evaluation.service;

import com.bci.evaluation.commons.DtoMapper;
import com.bci.evaluation.commons.exception.JwtException;
import com.bci.evaluation.commons.exception.UserNotFoundException;
import com.bci.evaluation.controller.dto.LoginResponseDto;
import com.bci.evaluation.controller.dto.SignUpRequestDto;
import com.bci.evaluation.controller.dto.SignUpResponseDto;
import com.bci.evaluation.repository.AuthoritiesRepository;
import com.bci.evaluation.repository.Authority;
import com.bci.evaluation.repository.Phone;
import com.bci.evaluation.repository.PhoneRepository;
import com.bci.evaluation.repository.User;
import com.bci.evaluation.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.bci.evaluation.commons.DtoMapper.mapUserToLoginResponseDto;
import static com.bci.evaluation.commons.DtoMapper.mapUserToSignUpResponseDto;

@Service
public class UsersService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PhoneRepository phoneRepository;
    private JwtService jwtService;

    @Autowired
    public UsersService(PasswordEncoder passwordEncoder,
                        UserRepository userRepository,
                        AuthoritiesRepository authoritiesRepository,
                        JwtService jwtService,
                        PhoneRepository phoneRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.jwtService = jwtService;
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public SignUpResponseDto saveUser(SignUpRequestDto userDto) {
        User user = DtoMapper.mapUserDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String token;
        try {
            user = userRepository.save(user);
            Authority authority = new Authority(user);
            authoritiesRepository.save(authority);

            for (Phone phone : user.getPhones()) {
                phone.setUser(user);
                phoneRepository.save(phone);
            }

            token = jwtService.generateToken(user.getUsername());

        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            throw new DataIntegrityViolationException("Username " + user.getUsername() + " already exists");
        } catch (JOSEException ex) {
            throw new JwtException("There was an error creating the token for: " + user.getUsername());
        }

        return mapUserToSignUpResponseDto(user, token);
    }

    public LoginResponseDto findUser(String username) {
        String token;
        User user;
        try {
            user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UserNotFoundException("User " + username + " not found");
            }
            user.setLastLogin(Timestamp.from(Instant.now()));
            user = userRepository.save(user);
            token = jwtService.generateToken(user.getUsername());
        } catch (JOSEException ex) {
            throw new JwtException("There was an error creating the token for: " + username);
        }

        return mapUserToLoginResponseDto(user, token);


    }


}
