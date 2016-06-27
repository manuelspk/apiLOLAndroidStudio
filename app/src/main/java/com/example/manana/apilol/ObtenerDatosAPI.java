package com.example.manana.apilol;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by manana on 15/6/16.
 */


public class ObtenerDatosAPI extends AsyncTask<String, Void, Bitmap> {

    private MainActivity ventana;
    public Usuario usuario;

    static final String API_KEY = "4ab45b6d-89ca-4679-aaaa-95c75c00a6c5";                               //Mi key.
    static final String API_URL = "https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/";         //URL de la API.

    private TextView lblNombre;         //Campos donde vamos a pintar los datos devueltos por la API.
    private TextView lblNivel;
    private TextView lblIcono;
    private ImageView imageView;

    private Button btnUltimasPartidas;
    private Button btnEstadisticas;

    public ObtenerDatosAPI(MainActivity ventana, TextView lblNombre, TextView lblNivel, TextView lblIcono, ImageView imageView, Button btnUltimasPartidas, Button btnEstadisticas) {
        this.ventana = ventana;
        this.lblNombre = lblNombre;
        this.lblNivel = lblNivel;
        this.lblIcono = lblIcono;
        this.imageView = imageView;
        this.btnUltimasPartidas=btnUltimasPartidas;
        this.btnEstadisticas=btnEstadisticas;
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        try {

            //params[0]=nombre de jugador;

            URL url = new URL(API_URL + params[0] + "?api_key=" + API_KEY);                 //Construimos la URL. Params[0] es el nombre del jugador.
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();     //Abrimos la conexión.
            try {
                //Leemos el JSON que recibimos y lo devolvemos.

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();


                JSONObject object = (JSONObject) new JSONTokener(stringBuilder.toString()).nextValue();
                object = object.getJSONObject(String.valueOf(params[0]));

                String miJson = object.toString();

                Gson miGson = new Gson();
                usuario= miGson.fromJson(miJson,Usuario.class);

                //usuario.setName(object.getJSONObject(String.valueOf(params[0])).getString("name"));
                //usuario.setId(object.getJSONObject(String.valueOf((params[0]))).getLong("id"));
                //usuario.setProfileIconId(object.getJSONObject(String.valueOf((params[0]))).getInt("profileIconId"));
                //usuario.setRevisionDate(object.getJSONObject(String.valueOf((params[0]))).getLong("revisionDate"));
                //usuario.setSummonerLevel(object.getJSONObject(String.valueOf((params[0]))).getLong("summonerLevel"));

                ventana.setUsuario(usuario);





                URL urlAvatar = new URL("http://ddragon.leagueoflegends.com/cdn/6.12.1/img/profileicon/"+usuario.getProfileIconId()+".png");

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
        lblNombre.setText(usuario.getName());
        lblNivel.setText(String.valueOf(usuario.getSummonerLevel()));
        lblIcono.setText(String.valueOf(usuario.getProfileIconId()));

        if(response != null) {
            imageView.setImageBitmap(response);
        }

        btnEstadisticas.setEnabled(true);
        btnUltimasPartidas.setEnabled(true);

        btnEstadisticas.setBackgroundColor(0xFF1B98E0);
        btnUltimasPartidas.setBackgroundColor(0xFF1B98E0);

        btnEstadisticas.setBackgroundResource(R.drawable.bordesredondeados);
        btnUltimasPartidas.setBackgroundResource(R.drawable.bordesredondeados);
    }
}
