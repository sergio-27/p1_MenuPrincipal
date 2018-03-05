package com.stucom.alu2015018.p1_menuprincipal.ranking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.adapter.GameRankingAdapter;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.GameList;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.Game;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.GameRanking;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.GameRankingList;
import com.stucom.alu2015018.p1_menuprincipal.tasks.SearchGameDetailsTask;


public class RankingActivity extends AppCompatActivity
        implements SearchGameDetailsTask.WeakReference, GameRankingAdapter.OnItemClickListener {


    private ImageView imgView;
    private TextView gameNameView, gameDescriptionView;
    private Game[] games;
    private RecyclerView rvRanking;
    private GameList gameList;
    static String json;
    private String gameName, gameDescription, imgPath;
    private ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        pb = findViewById(R.id.progressBar);

        pb.setVisibility(View.GONE);

        //obtenemos el id, nombre, descripcion y ruta de imagen
        Intent intent = getIntent();
        String gameID = intent.getStringExtra("Game ID");
        //obtenemos el json del game seleccionad
        json = intent.getStringExtra("game");

        Gson gson = new Gson();

        gameList = gson.fromJson(json, GameList.class);

        games = gameList.getResultGames();

        for (int i = 0; i < games.length; i ++){
            if (games[i].getGameName().equals(intent.getStringExtra("selectedGame"))){
                gameName = games[i].getGameName();
                gameDescription = games[i].getDescription();
                imgPath = games[i].getImgPath();
            }
        }


        imgView = findViewById(R.id.gameImage);
        gameNameView = findViewById(R.id.gameName);
        gameDescriptionView = findViewById(R.id.gameDescription);
        rvRanking = findViewById(R.id.rvRanking);

        rvRanking.setAdapter(null);

        gameNameView.setText(gameName);
        gameDescriptionView.setText(gameDescription);
        Picasso.with(this).load(imgPath).into(imgView);

        new SearchGameDetailsTask(this).execute(gameID);


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finished(GameRankingList list) {
        GameRankingAdapter adapter = new GameRankingAdapter(list);
        //Log.d("sergio", "Score: " + list.getGameData().getGameRankings()[0].getScore());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvRanking.setLayoutManager(layoutManager);
        rvRanking.setItemAnimator(new DefaultItemAnimator());
        rvRanking.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }


    @Override
    public void itemClicked(View view, GameRanking gameRanking) {
        String userName = gameRanking.getUser().getUsername();
        Toast.makeText(view.getContext(), "User Name: " + userName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UserRankingDetails.class);
        intent.putExtra("userId", String.valueOf(gameRanking.getUser().getUserId()));
//        Log.d("sergio","User ID: " + String.valueOf(gameRanking.getUser().getUserId()));
        startActivity(intent);
    }
}
