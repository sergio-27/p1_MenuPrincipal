package com.stucom.alu2015018.p1_menuprincipal.game;

import com.stucom.alu2015018.p1_menuprincipal.user.User;

/**
 * Created by Sergio on 03/01/2018.
 */

public class GameRanking {

    private int id;
    private int user_id;
    private int game_id;
    private int score;
    private User user;


    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public int getScore() {
        return score;
    }

    public User getUser() {
        return user;
    }
}
