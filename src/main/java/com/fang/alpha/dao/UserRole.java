package com.fang.alpha.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserRole {

    @Id
    @Column
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private String role;

    @Column
    private String email;

    public UserRole(int id, String username, String password, String salt, String role, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.role = role;
        this.email = email;
    }

    public UserRole() {
    }

    public UserRole(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id &&
                Objects.equals(username, userRole.username) &&
                Objects.equals(password, userRole.password) &&
                Objects.equals(salt, userRole.salt) &&
                Objects.equals(role, userRole.role) &&
                Objects.equals(email, userRole.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, salt, role, email);
    }
}
