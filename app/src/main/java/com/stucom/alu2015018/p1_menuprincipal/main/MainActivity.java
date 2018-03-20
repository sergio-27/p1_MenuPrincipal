package com.stucom.alu2015018.p1_menuprincipal.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.ajustes.AjustesActivity;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.ChooseGame;
import com.stucom.alu2015018.p1_menuprincipal.gamep3.GameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_jugar = (Button) findViewById(R.id.btn_jugar);
        Button btn_ranking = (Button) findViewById(R.id.btn_ranking);
        Button btn_ajustes = (Button) findViewById(R.id.btn_ajustes);
        Button btn_about = (Button) findViewById(R.id.btn_enCuantoA) ;

        //jugar
        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jugar_intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(jugar_intent);
            }
        });

        //ranking
        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ranking_intent = new Intent(MainActivity.this, ChooseGame.class);
                startActivity(ranking_intent);
            }
        });

        //ajustes
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajustes_intent = new Intent(MainActivity.this, AjustesActivity.class);
                startActivity(ajustes_intent);
            }
        });

        //en cuanto a
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/sergio-ruiz-sanchez-ba406b11a/");
                Intent about_intent = new Intent(Intent.ACTION_VIEW);
                about_intent.setData(uri);
                startActivity(about_intent);
            }
        });
    }
}
