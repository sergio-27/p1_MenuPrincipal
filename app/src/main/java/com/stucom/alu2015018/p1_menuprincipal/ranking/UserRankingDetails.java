package com.stucom.alu2015018.p1_menuprincipal.ranking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.adapter.UserRankingAdapter;
import com.stucom.alu2015018.p1_menuprincipal.tasks.SearchUserRankingTask;
import com.stucom.alu2015018.p1_menuprincipal.user.User;
import com.stucom.alu2015018.p1_menuprincipal.user.UserList;

public class UserRankingDetails extends AppCompatActivity
implements SearchUserRankingTask.WeakReference{

    TextView tvUsername, tvFirstName, tvLastName, tvUserEmail;
    ImageView imgUser;
    RecyclerView rvUserRankingItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ranking_details);

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvUsername = findViewById(R.id.tvUsername);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        imgUser = findViewById(R.id.imgUser);
        rvUserRankingItem = findViewById(R.id.rvUserRankingItem);

        //obtenemos el id del usuario clickado para utilizarlo en la task para obtener su ranking
        Intent intent = getIntent();

        String userID = intent.getStringExtra("userId");

        //Log.d("sergio","User ID: " + userID);

        new SearchUserRankingTask(this).execute(userID);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finished(UserList userList) {
        User user = userList.getResultUsers();
        tvUsername.setText(user.getUsername());
        tvFirstName.setText(user.getFirstname());
        tvLastName.setText(user.getLastname());
        tvUserEmail.setText(user.getEmail());
        Picasso.with(this).load(user.getAvatar_path()).into(imgUser);

        //creamos un adapter para mostrar los items user_ranking_item
        UserRankingAdapter adapter = new UserRankingAdapter(userList);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rvUserRankingItem.setLayoutManager(manager);
        rvUserRankingItem.setItemAnimator(new DefaultItemAnimator());
        rvUserRankingItem.setAdapter(adapter);

    }
}
