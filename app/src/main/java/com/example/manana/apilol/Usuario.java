package com.example.manana.apilol;

import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by manana on 7/6/16.
 */
public class Usuario extends AsyncTask<String, Void, String>{

    private MainActivity ventana;
    private TextView lblNombre;         //Campos donde vamos a pintar los datos devueltos por la API.
    private TextView lblNivel;
    private TextView lblIcono;
    private ImageView imageView;

    long id;                            //Número de identificación del usuario.
    String summonerName;                //Nombre de nuestro jugador.
    String name;                        //Nombre de nuestro jugador (El que devuelve la API)
    int profileIconId;                  //El número de avatar del jugador. Lo usaremos para obtener la imagen a través de los datos estáticos de la API.
    long revisionDate;                  //Long que indica si ha habido alguna modificación en el perfil.
    long summonerLevel;                 //Nivel del jugador

    static final String API_KEY = "4ab45b6d-89ca-4679-aaaa-95c75c00a6c5";                               //Mi key.
    static final String API_URL = "https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/";         //URL de la API.


    //Contructor que le pasamos los campos de nuestro activity Main.
    public Usuario(TextView lblNombre, TextView lblNivel, TextView lblIcono, String Invocador, ImageView imageView)
    {
        this.lblNombre=lblNombre;
        this.lblNivel=lblNivel;
        this.lblIcono=lblIcono;
        this.summonerName=Invocador;
        this.imageView=imageView;
    }



    //Método que se ejecutará en segundo plano al hacer la llamada en MainActivity.
    @Override
    protected String doInBackground(String... params) {
        try {
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
                return stringBuilder.toString();
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
    protected void onPostExecute(String response) {
        if(response == null) {
            response = "HUBO UN ERROR";
        }

        try {
            //Leemos cada campo de nuestro JSON y lo guardamos en los atributos del objeto. Finalmente lo imprimimos en pantalla.

            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            this.name = object.getJSONObject(String.valueOf(this.summonerName)).getString("name");
            this.id=object.getJSONObject(String.valueOf((this.summonerName))).getLong("id");
            this.profileIconId=object.getJSONObject(String.valueOf((this.summonerName))).getInt("profileIconId");
            this.revisionDate=object.getJSONObject(String.valueOf((this.summonerName))).getLong("revisionDate");
            this.summonerLevel=object.getJSONObject(String.valueOf((this.summonerName))).getLong("summonerLevel");

            ventana.setUsuario(this);

            lblNombre.setText(this.name);
            lblNivel.setText(String.valueOf(this.summonerLevel));
            lblIcono.setText(String.valueOf(this.profileIconId));


            //Llamamos a nuestra clase de obtener imágenes en un segundo hilo, usando el dato del avatar del jugador que nos ha devuelto la API.
            ObtenerImagen asyncTask = new ObtenerImagen(imageView);
            asyncTask.execute("http://ddragon.leagueoflegends.com/cdn/6.12.1/img/profileicon/"+this.profileIconId+".png");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
