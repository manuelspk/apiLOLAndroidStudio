package com.example.manana.apilol;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Trabajo on 28/06/2016.
 */
public class ObtenerEstadisticas extends AsyncTask<String, Void, Bitmap> {

    private List<Estadisticas> listaEstadistica;
    private EstadisticasActivity miVentana;


    public ObtenerEstadisticas(EstadisticasActivity miVentana) {
        this.miVentana=miVentana;

    }



    @Override
    protected Bitmap doInBackground(String... params) {
        try {

            listaEstadistica = new ArrayList<Estadisticas>();

            URL url = new URL("https://euw.api.pvp.net/api/lol/euw/v1.3/stats/by-summoner/"+params[0]+"/summary?season=SEASON2016&api_key=4ab45b6d-89ca-4679-aaaa-95c75c00a6c5");                 //Construimos la URL. Params[0] es el id del jugador.
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();     //Abrimos la conexión.
            try {
                InputStream stream = urlConnection.getInputStream();
                //List<Partida> partidas = Arrays.asList(gson.fromJson(stream,Partida[].class))
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();




                JSONObject jObject = (JSONObject) new JSONTokener(stringBuilder.toString()).nextValue();
                JSONArray partidas = jObject.getJSONArray("playerStatSummaries");

                String miJson = partidas.toString();

                Gson gson = new Gson();
                listaEstadistica = Arrays.asList(gson.fromJson(miJson, Estadisticas[].class));


                //Obtenemos avatar del usuario.


                URL urlAvatar = new URL("http://ddragon.leagueoflegends.com/cdn/6.12.1/img/profileicon/"+params[1]+".png");

                URLConnection connection = urlAvatar.openConnection();

                int imageSize = connection.getContentLength();

                InputStream is = connection.getInputStream();

                byte[] resultado = new byte[imageSize];

                byte[] buffer = new byte[1024];

                int byteTotalesLeidos = 0;

                int byteLeidosEnLaIteracion;

                while((byteLeidosEnLaIteracion = is.read(buffer)) != -1){

                    System.arraycopy(buffer, 0, resultado, byteTotalesLeidos, byteLeidosEnLaIteracion);

                    byteTotalesLeidos += byteLeidosEnLaIteracion;

                }

                return BitmapFactory.decodeByteArray(resultado, 0, imageSize);

            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            return null;
        }


    }





    @Override
    protected void onPostExecute(Bitmap response) {
        if(response != null) {

        miVentana.setListaEstadisticas(listaEstadistica);

        Estadisticas miEstadistica = new Estadisticas();

        for (Estadisticas i : listaEstadistica)
        {
            if(i.getPlayerStatSummaryType().equals("Unranked"))
            {
                miEstadistica = i;
            }
        }


        miVentana.lblMinionsNeutrales.setText( String.valueOf(miEstadistica.getAggregatedStats().getTotalNeutralMinionsKilled()));
        miVentana.lblTotalMinions.setText(String.valueOf(miEstadistica.getAggregatedStats().getTotalMinionKills()));
        miVentana.lblAsesinatos.setText(String.valueOf(miEstadistica.getAggregatedStats().getTotalChampionKills()));
        miVentana.lblAsistencias.setText(String.valueOf(miEstadistica.getAggregatedStats().getTotalAssists()));
        miVentana.lblTorretas.setText(String.valueOf(miEstadistica.getAggregatedStats().getTotalTurretsKilled()));
        miVentana.lblWins.setText(String.valueOf(miEstadistica.getWins()));



            miVentana.imagen.setImageBitmap(response);
        }
    }

}
