package com.stucom.alu2015018.p1_menuprincipal.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio on 02/01/2018.
 */

public class User {

    @SerializedName("id")
    private int userId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String avatar_path;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }
}
