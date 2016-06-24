package com.example.manana.apilol;

import android.widget.TextView;

/**
 * Created by manana on 24/6/16.
 */
public class PartidasViewHolder {
    private TextView tvChampion;
    private TextView tvWin;
    private TextView tvKills;
    private TextView tvDeaths;
    private TextView tvAssists;
    private Partida item;


    public PartidasViewHolder(TextView tvChampion, TextView tvWin, Partida item, TextView tvKills, TextView tvDeaths, TextView tvAssists ) {
        this.tvChampion = tvChampion;
        this.tvWin = tvWin;
        this.item = item;
        this.tvKills = tvKills;
        this.tvDeaths = tvDeaths;
        this.tvAssists = tvAssists;
        bindItem(item);
    }

    public void bindItem(Partida item){
        this.item=item;
        tvChampion.setText(item.getGameMode());
        tvWin.setText(item.getSubType());

        tvKills.setText(String.valueOf(item.getChampionsKilled()));
        tvDeaths.setText(String.valueOf(item.getNumDeaths()));
        tvAssists.setText(String.valueOf(item.getAssist()));
    }
}
