package com.example.manana.apilol;

        import android.content.Context;
        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.Serializable;
        import java.util.HashMap;
        import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText emailText;                                 //TextBox para introducir el nombre de jugador.

    private TextView lblNombre;                         //Label donde imprimiremos el nombre devuelto de la API.
    private TextView lblNivel;                          //Label donde imprimiremos el nivel devuelto de la API.
    private TextView lblIcono;
    private ImageView avatar;                           //ImageView donde se verá el avatar del jugador.
    private Button btnUltimasPartidas;
    private Button btnEstadisticas;

    public Usuario usuario;                            //Objeto de tipo usuario.

    private static final int REQUEST_ULTIMASPARTIDAS_ACTIVITY = 1;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        //Relacionamos nuestras variables con los campos del XML.

        emailText = (EditText) findViewById(R.id.emailText);

        lblNombre=(TextView) findViewById(R.id.lblNombre);
        lblNivel=(TextView) findViewById(R.id.lblNivel);
        lblIcono=(TextView) findViewById(R.id.lblIcono);
        avatar=(ImageView) findViewById(R.id.imageView);

        btnUltimasPartidas=(Button) findViewById(R.id.BtnUltimasPartidas);
        btnEstadisticas=(Button) findViewById(R.id.BtnEstadisticas);

        btnUltimasPartidas.setEnabled(false);                                       //Desactivo los botones para que no se pueda acceder si no se ha encontrado un usuario.
        btnEstadisticas.setEnabled(false);

        btnEstadisticas.setBackgroundResource(R.drawable.botonesdesactivados);
        btnUltimasPartidas.setBackgroundResource(R.drawable.botonesdesactivados);

        Button queryButton = (Button) findViewById(R.id.queryButton);

        //Escuchador de nuestro botón. Al hacer click en él.
        final MainActivity miVentana = this;


        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Ejecutamos un hilo secundario (ASyncTask) donde hará la llamada a la API y obtendremos nuestro datos de ella.
                ObtenerDatosAPI asyncTask = new ObtenerDatosAPI(miVentana, lblNombre, lblNivel, lblIcono, avatar, btnUltimasPartidas,btnEstadisticas);
                asyncTask.execute(emailText.getText().toString().toLowerCase());


                //Ocultar el teclado después del click en el botón.
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(emailText.getWindowToken(), 0);

            }
        });


        btnUltimasPartidas.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                //Lance la intencion de abrir la actividad secundaria
                Intent intent = new Intent(MainActivity.this, UltimasPartidas.class);
                intent.putExtra("usuario",  usuario);

                MainActivity.this.startActivityForResult(intent, REQUEST_ULTIMASPARTIDAS_ACTIVITY);
            }
        });


        btnEstadisticas.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                //Lance la intencion de abrir la actividad secundaria
                Intent intent = new Intent(MainActivity.this, EstadisticasActivity.class);
                intent.putExtra("usuario",  usuario);

                MainActivity.this.startActivityForResult(intent, REQUEST_ULTIMASPARTIDAS_ACTIVITY);
            }
        });
    }


}
