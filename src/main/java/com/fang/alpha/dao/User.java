package com.fang.alpha.dao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id
    @Column
    private int id;

    @Column
    private String password;

    @Column
    private Integer role;

    @Column
    private String salt;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String intro;

    @Column
    private String nickname;

    @Column
    private String header;

    public User() {
    }

    public User(int id, String username, String email, String intro, String nickname, String header) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.intro = intro;
        this.nickname = nickname;
        this.header = header;
    }

    public User(int id, String username, String intro, String nickname, String header) {
        this.id = id;
        this.username = username;
        this.intro = intro;
        this.nickname = nickname;
        this.header = header;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(intro, user.intro) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(header, user.header);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, password, role, salt, username, email, intro, nickname, header);
    }
}
