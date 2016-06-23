package com.example.manana.apilol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class UltimasPartidas extends AppCompatActivity {



    private List<Partida> listaPartidas;


    public void setListaPartidas(List<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_partidas);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        ObtenerUltimasPartidas asyncTask = new ObtenerUltimasPartidas();
        asyncTask.execute();


        


    }
}
