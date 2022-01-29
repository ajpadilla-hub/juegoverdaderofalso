package com.example.juegoverdaderofalso;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionExterna extends AsyncTask<Void, Void, Void> {

    private String error="";
    private ResultSet resultados;
    Connection connection;

    @Override
    protected Void doInBackground(Void... voids) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://pablotorres.ddns.net:3306/juego","alumno","Alumno1**");

            Statement statement = connection.createStatement();
            this.resultados = statement.executeQuery("SELECT * FROM preguntas");

        }
        catch (Exception e){
            error=e.toString();
        }
        return null;
    }

    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }

    protected void onPostExecute(Void avoid){

        try {
            connection.close();
        }
        catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    public ResultSet getResultados()
    {

        return this.resultados;
    }
}
