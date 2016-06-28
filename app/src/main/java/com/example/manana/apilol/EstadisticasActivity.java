package com.example.manana.apilol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EstadisticasActivity extends AppCompatActivity {


    private List<Estadisticas> listaEstadisticas;
    public TextView lblMinionsNeutrales;
    public TextView lblTotalMinions;
    public TextView lblAsesinatos;
    public TextView lblAsistencias;
    public TextView lblTorretas;
    public TextView lblWins;
    public ImageView imagen;


    public void setListaEstadisticas(List<Estadisticas> listaEstadisticas) {
        this.listaEstadisticas = listaEstadisticas;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        lblMinionsNeutrales = (TextView) findViewById(R.id.minionsNeutralesEst);
        lblTotalMinions = (TextView) findViewById(R.id.totalMinionsEst);
        lblAsesinatos = (TextView) findViewById(R.id.asesinatosEst);
        lblAsistencias=(TextView) findViewById(R.id.totalAsistEst);
        lblTorretas= (TextView) findViewById(R.id.turretsEst);
        lblWins = (TextView) findViewById(R.id.winsEst);
        imagen = (ImageView) findViewById(R.id.imgChampionEst);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        EstadisticasActivity miVentana = this;

        ObtenerEstadisticas asyncTask = new ObtenerEstadisticas(miVentana);
        asyncTask.execute(String.valueOf(usuario.getId()), String.valueOf(usuario.getProfileIconId()));

    }
}
