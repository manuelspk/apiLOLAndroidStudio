package com.example.manana.apilol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UltimasPartidas extends AppCompatActivity {



    private List<Partida> listaPartidas;
    private ListView lvPartidas;


    public void setListaPartidas(List<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_partidas);

        lvPartidas=(ListView)findViewById(R.id.lvPartidas);

        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        final UltimasPartidas miVentanaPartidas = this;

        ObtenerUltimasPartidas asyncTask = new ObtenerUltimasPartidas(miVentanaPartidas, lvPartidas);
        asyncTask.execute(String.valueOf(usuario.getId()));


        lvPartidas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UltimasPartidas.this, VisorPartida.class);

                intent.putExtra("POSICION", position);
                intent.putExtra("PARTIDA",  listaPartidas.get(position));
                startActivity(intent);

            }
        });


    }
}
