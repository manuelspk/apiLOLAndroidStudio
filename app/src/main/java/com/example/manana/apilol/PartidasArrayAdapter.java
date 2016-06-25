package com.example.manana.apilol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by manana on 24/6/16.
 */
public class PartidasArrayAdapter extends BaseAdapter {

    private List<Partida> data;

    public PartidasArrayAdapter(List<Partida> data) {
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View oldView, ViewGroup parent) {
        Partida partida = data.get(position);

        if(oldView==null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            oldView=inflater.inflate(R.layout.partidas_listitem,parent,false);

            TextView tvChampion = (TextView) oldView.findViewById(R.id.lblChampion);
            TextView tvWin = (TextView) oldView.findViewById(R.id.lblWin);

            TextView tvKills = (TextView) oldView.findViewById(R.id.lblKills);
            TextView tvDeaths = (TextView) oldView.findViewById(R.id.lblDeaths);
            TextView tvAssists = (TextView) oldView.findViewById(R.id.lblAssists);

            ImageView imgChampion = (ImageView) oldView.findViewById(R.id.imgChampion);



            PartidasViewHolder partidasViewHolder = new PartidasViewHolder(tvChampion,tvWin,partida, tvKills,tvDeaths,tvAssists,imgChampion, parent);
            oldView.setTag(partidasViewHolder);
        }else{
            ((PartidasViewHolder)oldView.getTag()).bindItem(partida);
        }

        return oldView;
    }
}
