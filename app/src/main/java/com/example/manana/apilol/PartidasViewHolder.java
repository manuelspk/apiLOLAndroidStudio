package com.example.manana.apilol;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manana on 24/6/16.
 */
public class PartidasViewHolder  {
    private TextView tvChampion;
    private TextView tvWin;
    private TextView tvKills;
    private TextView tvDeaths;
    private TextView tvAssists;
    private ImageView imgChampion;
    private ViewGroup parent;
    private Partida item;


    public PartidasViewHolder(TextView tvChampion, TextView tvWin, Partida item, TextView tvKills, TextView tvDeaths, TextView tvAssists, ImageView imgChampion, ViewGroup parent) {
        this.tvChampion = tvChampion;
        this.tvWin = tvWin;
        this.item = item;
        this.tvKills = tvKills;
        this.tvDeaths = tvDeaths;
        this.tvAssists = tvAssists;
        this.imgChampion = imgChampion;
        this.parent = parent;

        bindItem(item);
    }

    public void bindItem(Partida item) {
        Map<Integer, String> miMapaPersonajes = new HashMap<Integer, String>();
        miMapaPersonajes = new DatosEstaticos().devolverMapaPersonajes(miMapaPersonajes);

        this.item = item;
        tvChampion.setText(miMapaPersonajes.get(item.getChampionId()));
        tvWin.setText(item.getSubType());

        tvKills.setText(String.valueOf(item.getChampionsKilled()));
        tvDeaths.setText(String.valueOf(item.getNumDeaths()));
        tvAssists.setText(String.valueOf(item.getAssist()));



        int id = parent.getResources().getIdentifier(miMapaPersonajes.get(item.getChampionId()).toLowerCase(), "drawable", "com.example.manana.apilol");
        imgChampion.setImageResource(id);

        //imgChampion.setImageResource(R.drawable.ashe);

    }
}
