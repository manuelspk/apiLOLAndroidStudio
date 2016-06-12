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

                Usuario usuario = new Usuario(lblNombre,lblNivel,lblIcono, "manuelspk");
                usuario.execute(emailText.getText().toString());
            }
        });
    }


}
