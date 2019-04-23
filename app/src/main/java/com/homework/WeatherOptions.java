package com.homework;

import java.util.ArrayList;
import java.util.List;

public enum WeatherOptions {
    Temperature(R.drawable.temperature),Precipitations(R.drawable.weather),
    Humidity(R.drawable.humidity),Wind_speed(R.drawable.wind),
    Pressure(R.drawable.pressure);
    private int id;

    WeatherOptions(int id){
        this.id = id;
    }

    public int getID() {
        return id;
    }

    static Integer[] getAllWeatherOptions(){
        List<Integer> allOptions = new ArrayList<>();
        for(WeatherOptions option : WeatherOptions.values()){
            allOptions.add(option.getID());
        }
        return allOptions.toArray(new Integer[0]);
    }
}
