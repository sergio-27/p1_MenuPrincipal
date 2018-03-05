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
import com.stucom.alu2015018.p1_menuprincipal.gamep1.GameRanking;
import com.stucom.alu2015018.p1_menuprincipal.gamep1.GameRankingList;

/**
 * Created by Sergio on 03/01/2018.
 */

public class GameRankingAdapter
        extends RecyclerView.Adapter<GameRankingAdapter.ViewHolder> {


    private GameRankingList list;

    public GameRankingAdapter(GameRankingList list) {
        super();
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class GameRankingViewHolder extends ViewHolder {

        GameRanking gameRanking;
        ImageView imgAvatar;
        TextView userName, userScore;

        public GameRankingViewHolder(View view) {
            super(view);
            imgAvatar = view.findViewById(R.id.imgAvatar);
            userName = view.findViewById(R.id.tvUsername);
            userScore = view.findViewById(R.id.tvScore);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (gameRanking == null) return;
                    if (listener != null) listener.itemClicked(view, gameRanking);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void itemClicked(View view, GameRanking gameRanking);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){ this.listener = listener; }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (list != null) {
            View itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.player_ranking_item, parent, false);
            return new GameRankingViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.player_ranking_item_empty, parent, false);
            return new ViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (list == null) return;

        GameRankingViewHolder vh = (GameRankingViewHolder) holder;

        GameRanking gameRanking = list.getGameData().getGameRankings()[position];
        Context context = vh.userScore.getContext();
        vh.gameRanking = gameRanking;
        vh.userScore.setText(String.valueOf(gameRanking.getScore()));
        //Log.d("sergio", "Puntuacion: " + gameRanking.getScore());
        vh.userName.setText(gameRanking.getUser().getUsername());
        //Log.d("sergio", "Username: " + gameRanking.getUser().getUsername());
        Picasso.with(context).load(gameRanking.getUser().getAvatar_path()).into(vh.imgAvatar);
    }

    @Override
    public int getItemCount() { return list.getCount(); }


}
