package com.example.manana.apilol;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by manana on 13/6/16.
 */
public class ObtenerImagen extends AsyncTask<String, Integer, Bitmap> {
    private final ImageView imageView;

    //Contructor que proporcione la referencia a los elementos visuales que se necesitan para
    //procesar el resultado y el progreso

    public ObtenerImagen(ImageView imageView) {
        this.imageView = imageView;
    }


    //Metodo que se ejecuta en un hilo secundario.
    @Override
    protected Bitmap doInBackground(String... params){

        //Mi tarea de larga duración.

        try {
            URL url = new URL(params[0]);

            URLConnection connection = url.openConnection();

            int imageSize = connection.getContentLength();

            InputStream is = connection.getInputStream();

            byte[] resultado = new byte[imageSize];

            byte[] buffer = new byte[1024];

            int byteTotalesLeidos = 0;

            int byteLeidosEnLaIteracion;

            while((byteLeidosEnLaIteracion = is.read(buffer)) != -1){

                System.arraycopy(buffer, 0, resultado, byteTotalesLeidos, byteLeidosEnLaIteracion);

                byteTotalesLeidos += byteLeidosEnLaIteracion;

                publishProgress(byteTotalesLeidos*100/imageSize);
            }

            return BitmapFactory.decodeByteArray(resultado, 0, imageSize);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            //Aquí no se puede lanzar un Toast, o mostrar una notificación.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    //Preparamos la interfaz de usuario para la eminente ejecución de la tarea de larga duración.
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Al terminar la ejecución, mostramos la imagen e nuestro correspondiente imageView.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
