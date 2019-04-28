package com.homework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class StoreData implements Serializable{
    private String city;
    private List<Integer> weatherOptions;
    private String duration;
    private static StoreData instance;

    private StoreData(String city,List<Integer> weatherOptions,String duration){
        this.duration = duration;
        this.city = city;
        this.weatherOptions = weatherOptions;
    }

    static StoreData getInstance(String city,List<Integer> weatherOptions,String duration){
        instance = new StoreData(city,weatherOptions,duration);
        return instance;
    }

    String getCity() {
        return city;
    }

    List<Integer> getWeatherOptions() {
        return weatherOptions;
    }

    String getDuration() {
        return duration;
    }

    void setCity(String city){this.city = city;}

    void setDuration(String duration){this.duration = duration;}

    void setWeatherOptions(List<Integer> weatherOptions){
        this.weatherOptions = weatherOptions;
    }

    boolean areDataEqual(StoreData data){
        return this.city.equals(data.getCity()) &&
                weatherOptions.containsAll(data.getWeatherOptions()) &&
                data.getWeatherOptions().containsAll(weatherOptions) &&
                this.duration.equals(data.getDuration());
    }
}
