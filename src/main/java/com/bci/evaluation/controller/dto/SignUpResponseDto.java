package com.bci.evaluation.controller.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class SignUpResponseDto {

    private UUID id;
    private Timestamp created;
    private Timestamp lastLogin;
    private String token;
    private boolean isActive;
    private String username;

    public SignUpResponseDto(UUID id, Timestamp created, Timestamp lastLogin, String token, boolean isActive, String username) {
        this.id = id;
        this.created = created;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
}
