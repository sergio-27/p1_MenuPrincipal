package com.stucom.alu2015018.p1_menuprincipal.game;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio on 19/12/2017.
 */

public class GameList {

    @SerializedName("data")
    private Game[] resultGames;

    public Game[] getResultGames() {
        return resultGames;
    }
}
