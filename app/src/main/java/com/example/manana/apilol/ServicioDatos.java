package com.example.manana.apilol;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by manana on 9/6/16.
 */
public class ServicioDatos extends AsyncTask<Void, Void, String> {

    private String API_KEY;
    private String API_URL;
    Usuario usuario = new Usuario();


    @Override
    protected String doInBackground(Void... params) {
        try {
            URL url = new URL(API_URL + usuario.summonerName + "?api_key=" + API_KEY);
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


    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        //progressBar.setVisibility(View.GONE);
        //Log.i("INFO", response);

        // TODO: check this.exception
        // TODO: do something with the feed

        try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//            usuario.name = object.getJSONObject(String.valueOf(emailText.getText())).getString("name");
//            usuario.id=object.getJSONObject(String.valueOf((emailText.getText()))).getLong("id");
//            usuario.profileIconId=object.getJSONObject(String.valueOf((emailText.getText()))).getInt("profileIconId");
//            usuario.revisionDate=object.getJSONObject(String.valueOf((emailText.getText()))).getLong("revisionDate");
//            usuario.summonerLevel=object.getJSONObject(String.valueOf((emailText.getText()))).getLong("summonerLevel");

//            lblNombre.setText(usuario.name);
//            lblNivel.setText(String.valueOf(usuario.summonerLevel));
//            lblIcono.setText(String.valueOf(usuario.profileIconId));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
