package com.stucom.alu2015018.p1_menuprincipal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stucom.alu2015018.p1_menuprincipal.R;
import com.stucom.alu2015018.p1_menuprincipal.user.UserList;
import com.stucom.alu2015018.p1_menuprincipal.user.UserRanking;

/**
 * Created by Sergio on 11/01/2018.
 */

public class UserRankingAdapter extends RecyclerView.Adapter<UserRankingAdapter.ViewHolder> {

    private UserList list;

    public UserRankingAdapter(UserList list) {
        //porque llamar a super?
        super();
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView gameName, tvGameDescription, tvUserScore;
        ImageView imgGame;

        public ViewHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.gameName);
            tvGameDescription = itemView.findViewById(R.id.tvGameDescription);
            tvUserScore = itemView.findViewById(R.id.tvUserScore);
            imgGame = itemView.findViewById(R.id.imgGame);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.user_ranking_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ViewHolder vh = holder;

        UserRanking userRanking = list.getResultUsers().getUserRanking()[position];

        Context context = vh.tvGameDescription.getContext();
        vh.gameName.setText(userRanking.getUserRankingGame().getGameName());
        vh.tvUserScore.setText(String.valueOf(userRanking.getScore()));
        vh.tvGameDescription.setText(userRanking.getUserRankingGame().getDescription());
        Picasso.with(context).load(userRanking.getUserRankingGame().getImgPath()).into(vh.imgGame);

    }

    @Override
    public int getItemCount() {
        return list.getCount();
    }
}
