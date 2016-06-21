package com.example.manana.apilol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class UltimasPartidas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_partidas);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        ObtenerUltimasPartidas asyncTask = new ObtenerUltimasPartidas();
        asyncTask.execute();


        


    }
}
