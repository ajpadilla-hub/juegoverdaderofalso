package com.example.juegoverdaderofalso;

import java.util.HashMap;

public class Hashmap {


public void h(){

    HashMap<String, String> capitalCities = new HashMap<String, String>();


    capitalCities.put("England", "London");
    capitalCities.put("Germany", "Berlin");
    capitalCities.put("Norway", "Oslo");
    capitalCities.put("USA", "Washington DC");

    for (String i : capitalCities.keySet()) {
        System.out.println(i);
    }






}



}
