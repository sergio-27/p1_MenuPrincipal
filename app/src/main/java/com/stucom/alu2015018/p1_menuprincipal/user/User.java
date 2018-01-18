package com.stucom.alu2015018.p1_menuprincipal.user;

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
    @SerializedName("ranking")
    private UserRanking[] userRanking;

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public UserRanking[] getUserRanking() {
        return userRanking;
    }
}
