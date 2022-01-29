package com.example.juegoverdaderofalso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    Button play, tutorial;
    BDinterna mibase;
    SQLiteDatabase db;
    ResultSet resultados;

    View.OnClickListener apantalaDeJuego = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, PantallaJuego.class);
            startActivity(intent);
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.btnPlay);
        play.setOnClickListener(apantalaDeJuego);
        tutorial = (Button) findViewById(R.id.btntutorial);
        TextView cajaCargando = (TextView) findViewById(R.id.cajaCargando);

        play.setVisibility(View.INVISIBLE);
        tutorial.setVisibility(View.INVISIBLE);

        mibase = new BDinterna(this);
        db = mibase.getWritableDatabase();

        /* conexion bd ex descargar datos */
        ConexionExterna conecta = new ConexionExterna();

        try {

            conecta.execute().get();
            this.resultados  = conecta.getResultados();

            while (resultados.next()){
                if (db != null) {
                    int id = resultados.getInt(1);
                    String pregunta = resultados.getString(2);
                    int respuesta = resultados.getInt(3);
                    db.execSQL("INSERT OR REPLACE INTO preguntas(id,pregunta,respuesta) VALUES('"+String.valueOf(id)+"','"+pregunta+"','"+String.valueOf(respuesta)+"')");

                }

                //insertar datos de bd-ex a bd-int




                play.setVisibility(View.VISIBLE);
                tutorial.setVisibility(View.VISIBLE);
                cajaCargando.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        /* conexion bd int intro datos */



        mibase.close();
        db.close();


    }



}