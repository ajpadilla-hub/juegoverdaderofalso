package com.example.juegoverdaderofalso;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PantallaJuego extends AppCompatActivity {

    TextView  cajaScore;
    Button btnVeradero, btnFalso;
    int aleatorio, puntuancion = 0;
    BDinterna mibase;
    SQLiteDatabase db;
    int respuesta;



    View.OnClickListener clickResponder = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if( view == btnVeradero){

                if(respuesta == 1){

                    puntuancion += 100;
                }else {
                    puntuancion -= 50;
                }
                cajaScore.setText("puntos: " + String.valueOf(puntuancion));
            }

            if( view == btnFalso){
                if( respuesta == 0 ){

                    puntuancion += 100;
                }else{

                    puntuancion -= 50;
                }
                cajaScore.setText("puntos: " + String.valueOf(puntuancion));
            }

            sacaPregunta();
        }
    };


    private void sacaPregunta(){

        Random random = new Random();
        mibase = new BDinterna(this);
        db = mibase.getWritableDatabase();
        TextView cajaPregunta1 = (TextView) findViewById(R.id.cajaPregunta1);

        if (db != null){


            aleatorio = random.nextInt( 12 )+1;
            Cursor c = db.rawQuery( "SELECT * FROM preguntas WHERE id = "+ String.valueOf(aleatorio),null );

            if (c != null && c.moveToFirst()){
                c.moveToFirst();
                int id = c.getInt(0);
                String pregunta = c.getString(1);
                respuesta = c.getInt(2);

                cajaPregunta1.setText(pregunta);
                cajaScore.setText( "puntos: " +String.valueOf(puntuancion));

                mibase.close();
                db.close();

            }

        }




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego);





        cajaScore = (TextView) findViewById(R.id.cajaScore);
        btnVeradero = (Button) findViewById(R.id.btnVerdadero);
        btnFalso = (Button) findViewById(R.id.btnFalso);
        btnVeradero.setOnClickListener(clickResponder);
        btnFalso.setOnClickListener(clickResponder);
        sacaPregunta();



    }
}