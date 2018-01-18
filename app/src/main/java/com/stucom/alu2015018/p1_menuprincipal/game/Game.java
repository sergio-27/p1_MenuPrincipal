package com.stucom.alu2015018.p1_menuprincipal.game;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio on 19/12/2017.
 */

public class Game {

    @SerializedName("id")
    private int idGame;
    @SerializedName("name")
    private String gameName;
    private String description;
    @SerializedName("image_path")
    private String imgPath;
    @SerializedName("ranking")
    private GameRanking[] gameRankings;



    public int getIdGame() {
        return idGame;
    }

    public String getGameName() {
        return gameName;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public GameRanking[] getGameRankings() {
        return gameRankings;
    }
}
