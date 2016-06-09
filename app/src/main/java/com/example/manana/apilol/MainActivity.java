package com.example.manana.apilol;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import org.json.JSONTokener;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;


public class MainActivity extends AppCompatActivity {

    EditText emailText;
    ProgressBar progressBar;

    private TextView lblNombre;
    private TextView lblNivel;
    private TextView lblIcono;

    static final String API_KEY = "4ab45b6d-89ca-4679-aaaa-95c75c00a6c5";
    static final String API_URL = "https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/";

    Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = (EditText) findViewById(R.id.emailText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        lblNombre=(TextView) findViewById(R.id.lblNombre);
        lblNivel=(TextView) findViewById(R.id.lblNivel);
        lblIcono=(TextView) findViewById(R.id.lblIcono);


        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario.summonerName=emailText.getText().toString();
                new RetrieveFeedTask().execute();
            }
        });
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(Void... urls) {

            // Do some validation here

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
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);

            // TODO: check this.exception
            // TODO: do something with the feed

            try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
                usuario.name = object.getJSONObject(String.valueOf(usuario.summonerName)).getString("name");
                usuario.id=object.getJSONObject(String.valueOf((usuario.summonerName))).getLong("id");
                usuario.profileIconId=object.getJSONObject(String.valueOf((usuario.summonerName))).getInt("profileIconId");
                usuario.revisionDate=object.getJSONObject(String.valueOf((usuario.summonerName))).getLong("revisionDate");
                usuario.summonerLevel=object.getJSONObject(String.valueOf((usuario.summonerName))).getLong("summonerLevel");

                lblNombre.setText(usuario.name);
                lblNivel.setText(String.valueOf(usuario.summonerLevel));
                lblIcono.setText(String.valueOf(usuario.profileIconId));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
