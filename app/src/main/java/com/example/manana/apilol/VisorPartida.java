package com.example.manana.apilol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class VisorPartida extends AppCompatActivity {

    private TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_partida);

        prueba=(TextView)findViewById(R.id.textPrueba);

        Intent intent = getIntent();
        int temp = intent.getIntExtra("POSICION", 0);
        Partida partida = new Partida();
        partida = (Partida) getIntent().getSerializableExtra("PARTIDA");

        prueba.setText(String.valueOf(partida.getChampionId()));
    }
}
