package com.stucom.alu2015018.p1_menuprincipal.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.game.Game;
import com.stucom.alu2015018.p1_menuprincipal.game.GameRanking;
import com.stucom.alu2015018.p1_menuprincipal.game.GameRankingList;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Sergio on 03/01/2018.
 */

public class SearchGameDetailsTask extends AsyncTask<String, Void, GameRankingList> {

    String json;
    Game game;

    public interface WeakReference {
        Context getContext();
        void finished(GameRankingList list);
    }

    private WeakReference ref;

    public SearchGameDetailsTask(WeakReference ref) {
        super();
        this.ref = ref;
    }


    @Override
    protected GameRankingList doInBackground(String... strings) {

        InputStream in = null;
        try {

            String gameId = strings[0];
            gameId = URLEncoder.encode(gameId, "utf-8");

            //Log.d("sergio", "Game ID: " + gameId);

            String searchDetails = ref.getContext().getString(R.string.searchGameDetails, gameId);
            //Log.d("sergio", "URL: " + searchDetails);

            URL url = new URL(searchDetails);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(1000);
            con.setReadTimeout(2000);

            in = url.openStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int total = 0, nRead;
            while ((nRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, nRead);
                total += nRead;
            }

            json = new String(out.toByteArray());
            //Log.d("sergio", "JSON: " + json);

            Gson gson = new Gson();

            GameRankingList gameRankingList = gson.fromJson(json, GameRankingList.class);


//            game = gameRankingList.getGameData();
//
//            GameRanking[] gameRankings = game.getGameRankings();
//
//            for (GameRanking gr : gameRankings) {
//
//                Log.d("sergio", "Puntuacion: " + gr.getScore());
//            }


            return gameRankingList;

        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    protected void onPostExecute(GameRankingList gameRankingList) {
        ref.finished(gameRankingList);
    }
}
