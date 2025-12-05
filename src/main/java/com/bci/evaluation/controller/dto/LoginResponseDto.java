package com.bci.evaluation.controller.dto;

import java.sql.Timestamp;
import java.util.List;

public class LoginResponseDto {

    private String id;
    private Timestamp created;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;

    public LoginResponseDto(String id, Timestamp created, Timestamp lastLogin, String token, boolean isActive, String name, String email, String password, List<PhoneDto> phones) {
        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }
}
