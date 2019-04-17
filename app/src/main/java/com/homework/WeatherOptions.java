package com.homework;

import java.util.ArrayList;
import java.util.List;

public enum WeatherOptions {
    Temperature("Temperature"),Precipitations("Precipitations"),Humidity("Humidity"),
    Wind_speed("Wind speed"),Pressure("Pressure");
    private String name;

    WeatherOptions(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static String[] getAllWeatherOptions(){
        List<String> allOptions = new ArrayList<>();
        for(WeatherOptions option : WeatherOptions.values()){
            allOptions.add(option.getName());
        }
        return allOptions.toArray(new String[0]);
    }
}
