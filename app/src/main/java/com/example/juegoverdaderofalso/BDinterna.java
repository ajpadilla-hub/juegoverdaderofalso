package com.example.juegoverdaderofalso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDinterna extends SQLiteOpenHelper {
    private static int version = 1;
    private static String nombre = "mibase.sqlite";
    private static final String crearTabla = "CREATE TABLE preguntas (id INTEGER, pregunta TEXT, respuesta INTEGER)";


    public BDinterna (Context context){

        super(context, nombre, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(crearTabla);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
