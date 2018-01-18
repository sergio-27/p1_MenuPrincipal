package com.stucom.alu2015018.p1_menuprincipal.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alu2015018 on 09/01/2018.
 */

public class UserList {

    @SerializedName("data")
    private User resultUsers;
    private int count;

    public User getResultUsers() {
        return resultUsers;
    }

    public int getCount() {

        return resultUsers.getUserRanking().length;
    }
}
