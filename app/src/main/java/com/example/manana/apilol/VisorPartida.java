package com.example.manana.apilol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VisorPartida extends AppCompatActivity {

    private TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_partida);

        ImageView imgChampion = (ImageView) findViewById(R.id.imgChampionMatch);

        TextView lblChampion = (TextView) findViewById(R.id.championMatch);
        TextView lblFecha = (TextView) findViewById(R.id.fechaMatch);
        TextView lblModo = (TextView) findViewById(R.id.modoMatch);
        TextView lblTipo = (TextView) findViewById(R.id.tipoMatch);

        TextView lblNivel = (TextView) findViewById(R.id.nivelMatch);
        TextView lblAsesinatos = (TextView) findViewById(R.id.asesinatosMatch);
        TextView lblMuertes = (TextView) findViewById(R.id.muertesMatch);
        TextView lblAsistencias = (TextView) findViewById(R.id.asistenciasMatch);

        TextView lblTotalDanoEmitido = (TextView) findViewById(R.id.totalDanoEmitidoMatch);
        TextView lblTotalDanoRecibido = (TextView) findViewById(R.id.totalDanoRecibidoMatch);
        TextView lblTotalDanoCampeones = (TextView) findViewById(R.id.totalDanoCampeonesMatch);

        TextView lblDanoMagicoEmitido = (TextView) findViewById(R.id.danoMagicoEmitidoMatch);
        TextView lblDanoMagicoRecibido = (TextView) findViewById(R.id.danoMagicoRecibidoMatch);
        TextView lblDanoMagicoCampeon = (TextView) findViewById(R.id.danoMagicoCampeonMatch);

        TextView lblTriple = (TextView) findViewById(R.id.asesinatoTripleMatch);
        TextView lblQuadra = (TextView) findViewById(R.id.quadrakillMatch);
        TextView lblPenta = (TextView) findViewById(R.id.pentakillMatch);

        TextView lblTorretas = (TextView) findViewById(R.id.torretasDestruidasMatch);
        TextView lblSubditos = (TextView) findViewById(R.id.subditosMatch);


        Intent intent = getIntent();

        Partida partida = (Partida) getIntent().getSerializableExtra("PARTIDA");

        Map<Integer, String> miMapaPersonajes = new HashMap<Integer, String>();
        miMapaPersonajes = new DatosEstaticos().devolverMapaPersonajes(miMapaPersonajes);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.format(new Date(partida.getCreateDate()));


        int id = getResources().getIdentifier(miMapaPersonajes.get(partida.getChampionId()).toLowerCase(), "drawable", "com.example.manana.apilol");
        imgChampion.setImageResource(id);

        lblChampion.setText(miMapaPersonajes.get(partida.getChampionId()));
        lblFecha.setText(String.valueOf(String.valueOf(sdf.format(new Date(partida.getCreateDate())))));
        lblModo.setText(String.valueOf(partida.getGameMode()));
        lblTipo.setText(String.valueOf(partida.getGameType()));

        lblNivel.setText(String.valueOf(partida.getLevel()));
        lblAsesinatos.setText(String.valueOf(partida.getStats().getChampionsKilled()));
        lblMuertes.setText(String.valueOf(partida.getStats().getNumDeaths()));
        lblAsistencias.setText(String.valueOf(partida.getStats().getAssists()));

        lblTotalDanoEmitido.setText(String.valueOf(partida.getStats().getTotalDamageDealt()));
        lblTotalDanoRecibido.setText(String.valueOf(partida.getStats().getTotalDamageTaken()));
        lblTotalDanoCampeones.setText(String.valueOf(partida.getStats().getTotalDamageDealtToChampions()));

        lblDanoMagicoEmitido.setText(String.valueOf(partida.getStats().getMagicDamageDealtPlayer()));
        lblDanoMagicoRecibido.setText(String.valueOf(partida.getStats().getMagicDamageTaken()));
        lblDanoMagicoCampeon.setText(String.valueOf(partida.getStats().getMagicDamageDealtToChampions()));

        lblTriple.setText(String.valueOf(partida.getStats().getTripleKills()));
        lblQuadra.setText(String.valueOf(partida.getStats().getQuadraKills()));
        lblPenta.setText(String.valueOf(partida.getStats().getPentaKills()));

        lblTorretas.setText(String.valueOf(partida.getStats().getTurretsKilled()));
        lblSubditos.setText(String.valueOf(partida.getStats().getMinionsKilled()));

    }
}
