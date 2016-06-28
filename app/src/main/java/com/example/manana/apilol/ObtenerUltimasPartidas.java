package com.example.manana.apilol;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by manana on 21/6/16.
 */
public class ObtenerUltimasPartidas extends AsyncTask<String, Void, Void> {
    private List<Partida> listaPartidas;
    private UltimasPartidas miVentanaPartidas;
    private ListView lvPartidas;

    public ObtenerUltimasPartidas(UltimasPartidas miVentanaPartidas, ListView lvPartidas) {
        this.miVentanaPartidas=miVentanaPartidas;
        this.lvPartidas=lvPartidas;

    }

    @Override
    protected Void doInBackground(String... params) {

        try {

            listaPartidas = new ArrayList<Partida>();

            URL url = new URL("https://euw.api.pvp.net/api/lol/euw/v1.3/game/by-summoner/"+params[0]+"/recent?api_key=4ab45b6d-89ca-4679-aaaa-95c75c00a6c5");                 //Construimos la URL. Params[0] es el id del jugador.
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
                JSONArray partidas = jObject.getJSONArray("games");

                String miJson = partidas.toString();

                Gson gson = new Gson();
                listaPartidas = Arrays.asList(gson.fromJson(miJson, Partida[].class));


            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            return null;
        }

        return null;
    }




    @Override
    protected void onPostExecute(Void response) {
        miVentanaPartidas.setListaPartidas(listaPartidas);

        PartidasArrayAdapter adapter = new PartidasArrayAdapter(listaPartidas);
        lvPartidas.setAdapter(adapter);

    }


}
