package com.example.manana.apilol;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
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

    private TextView lblNombre;
    private TextView lblNivel;
    private TextView lblIcono;

    long id;
    String summonerName;
    String name;
    int profileIconId;
    long revisionDate;
    long summonerLevel;

    static final String API_KEY = "4ab45b6d-89ca-4679-aaaa-95c75c00a6c5";
    static final String API_URL = "https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/";

    public Usuario(TextView lblNombre, TextView lblNivel, TextView lblIcono, String Invocador)
    {
        this.lblNombre=lblNombre;
        this.lblNivel=lblNivel;
        this.lblIcono=lblIcono;
        this.summonerName=Invocador;
    }



    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(API_URL + params[0] + "?api_key=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
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
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }


    @Override


    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        Log.i("INFO", response);

        // TODO: check this.exception
        // TODO: do something with the feed

        try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            this.name = object.getJSONObject(String.valueOf(this.summonerName)).getString("name");
            this.id=object.getJSONObject(String.valueOf((this.summonerName))).getLong("id");
            this.profileIconId=object.getJSONObject(String.valueOf((this.summonerName))).getInt("profileIconId");
            this.revisionDate=object.getJSONObject(String.valueOf((this.summonerName))).getLong("revisionDate");
            this.summonerLevel=object.getJSONObject(String.valueOf((this.summonerName))).getLong("summonerLevel");

            lblNombre.setText(this.name);
            lblNivel.setText(String.valueOf(this.summonerLevel));
            lblIcono.setText(String.valueOf(this.profileIconId));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
