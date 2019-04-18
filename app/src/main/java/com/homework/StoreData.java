package com.homework;

import java.io.Serializable;
import java.util.ArrayList;

class StoreData implements Serializable{
    private String city;
    private ArrayList<String> weatherOptions;
    private String duration;
    private static StoreData instance;

    private StoreData(String city,ArrayList<String> weatherOptions,String duration){
        this.duration = duration;
        this.city = city;
        this.weatherOptions = weatherOptions;
    }

    static StoreData getInstance(String city,ArrayList<String> weatherOptions,String duration){
        instance = new StoreData(city,weatherOptions,duration);
        return instance;
    }

    String getCity() {
        return city;
    }

    ArrayList<String> getWeatherOptions() {
        return weatherOptions;
    }

    String getDuration() {
        return duration;
    }
}
