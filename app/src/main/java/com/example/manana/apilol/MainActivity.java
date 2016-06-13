package com.example.manana.apilol;

        import android.content.Context;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
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

    private TextView lblNombre;
    private TextView lblNivel;
    private TextView lblIcono;
    private ImageView imageView;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText = (EditText) findViewById(R.id.emailText);

        lblNombre=(TextView) findViewById(R.id.lblNombre);
        lblNivel=(TextView) findViewById(R.id.lblNivel);
        lblIcono=(TextView) findViewById(R.id.lblIcono);
        imageView=(ImageView) findViewById(R.id.imageView);



        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario(lblNombre,lblNivel,lblIcono, emailText.getText().toString(), imageView);
                usuario.execute(emailText.getText().toString());

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(emailText.getWindowToken(), 0);

            }
        });
    }


}
