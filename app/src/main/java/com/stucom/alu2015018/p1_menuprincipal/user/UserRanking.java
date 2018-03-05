package com.stucom.alu2015018.p1_menuprincipal.user;

import com.google.gson.annotations.SerializedName;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.Game;

/**
 * Created by alu2015018 on 09/01/2018.
 */

public class UserRanking {

    private int id;
    private int user_id;
    private int game_id;
    private int score;
    @SerializedName("game")
    private Game userRankingGame;

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

    public Game getUserRankingGame() {
        return userRankingGame;
    }
}
