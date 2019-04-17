package com.homework;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreData implements Serializable{
    private String city;
    private ArrayList<String> weatherOptions;
    private String duration;
    private static StoreData instance;

    private StoreData(String city,ArrayList<String> weatherOptions,String duration){
        this.duration = duration;
        this.city = city;
        this.weatherOptions = weatherOptions;
    }

    public static StoreData getInstance(String city,ArrayList<String> weatherOptions,String duration){
        instance = new StoreData(city,weatherOptions,duration);
        return instance;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<String> getWeatherOptions() {
        return weatherOptions;
    }

    public String getDuration() {
        return duration;
    }
}
