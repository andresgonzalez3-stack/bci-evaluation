package com.bci.evaluation.repository;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> authorities;
    private boolean enabled;
    private Timestamp created;
    private Timestamp lastLogin;
    private String name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Phone> phones;

    public User() {

    }

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.enabled = true;
        this.created = Timestamp.from(Instant.now());
        this.lastLogin = this.created;
        this.name = name;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
