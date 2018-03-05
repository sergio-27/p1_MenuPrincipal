package com.stucom.alu2015018.p1_menuprincipal.gamep1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.ranking.RankingActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ChooseGame extends AppCompatActivity {

    Spinner spinnerGame;
    Button btnChooseGame;
    Game[] resultGames;
    GameList listGames;
    List<String> gameNames = new ArrayList<>();
    Intent intent;
    String json, gameName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        spinnerGame = findViewById(R.id.spinnerGame);
        btnChooseGame = findViewById(R.id.chooseGame);

        new SearchGameNameTask().execute();

        btnChooseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameName = spinnerGame.getSelectedItem().toString();
                intent = new Intent(ChooseGame.this, RankingActivity.class);
                //Log.d("sergio","Selected Game: " + gameName);

                //buscamos en funcion del juego seleccionado su id para pasarlo con el intent
                getIdSelectedGame(gameName);

                Toast.makeText(view.getContext(), "Game Name: " + gameName, Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });


    }

    private void getIdSelectedGame(String name){

        for (Game game : resultGames){

            if (game.getGameName().equals(name)){
                intent.putExtra("Game ID", String.valueOf(game.getIdGame()));
                //Log.d("sergio","Game ID: " + game.getIdGame());
                intent.putExtra("game", json);
                intent.putExtra("selectedGame", gameName);
            }

        }
    }

    class SearchGameNameTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            InputStream ins = null;
            try {

                //para hacer llamadas y obtener datos no nos hace falta la apikey
                String urlGN = getResources().getString(R.string.searchGameURL);

                //Log.d("sergio", "URL: " + urlGN);

                URL url = new URL(urlGN);
                URLConnection con = url.openConnection();
                con.setReadTimeout(1000);
                con.setConnectTimeout(2000);


                ins = url.openStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int total = 0, nRead;
                while ((nRead = ins.read(buffer)) != -1) {
                    out.write(buffer, 0, nRead);
                    total += nRead;
                }
                //visualizamos el json descargado
                json = new String(out.toByteArray());
                //Log.d("sergio", "JSON: " + json);

                Gson gson = new Gson();

                listGames = gson.fromJson(json, GameList.class);

                resultGames = listGames.getResultGames();


                for (Game game : resultGames) {
                    gameNames.add(game.getGameName());
                    //Log.d("sergio", "Nombre: " + game.getGameName());
                }


            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //definimos un array adapter para guardar los nombres de los juegos y pasarlos a un spinner
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(spinnerGame.getContext(), android.R.layout.simple_spinner_item, gameNames);

            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerGame.setAdapter(spinnerArrayAdapter);

        }
    }

}