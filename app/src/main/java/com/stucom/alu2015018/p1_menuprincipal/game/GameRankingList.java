package com.stucom.alu2015018.p1_menuprincipal.game;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sergio on 03/01/2018.
 */

//clase utilizada para obtener los datos de un unico juego en la clase SearchGameDetailsTask,
// ya que
public class GameRankingList {

    private int count;
    @SerializedName("data")
    private Game gameData;

    //obtenemos el tamaño del array de GameRankings
    public int getCount() {
        return gameData.getGameRankings().length;
    }

    public Game getGameData() {
        return gameData;
    }

}
