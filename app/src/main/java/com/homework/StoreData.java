package com.homework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class StoreData implements Serializable{
    private static String city;
    private static List<Integer> weatherOptions;
    private static String duration;
    private static StoreData instance;

    private StoreData(String city,List<Integer> weatherOptions,String duration){
        StoreData.duration = duration;
        StoreData.city = city;
        StoreData.weatherOptions = weatherOptions;
    }

    static StoreData getInstance(String city,List<Integer> weatherOptions,String duration){
        instance = new StoreData(city,weatherOptions,duration);
        return instance;
    }

    static StoreData getSavedInstance(){
        if(instance == null)
            instance = new StoreData(null,new ArrayList<Integer>(),null);
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

    void setCity(String city){StoreData.city = city;}

    void setDuration(String duration){StoreData.duration = duration;}

    void setWeatherOptions(List<Integer> weatherOptions){
        StoreData.weatherOptions = weatherOptions;
    }

    boolean areDataEqual(StoreData data){
        return city.equals(data.getCity()) &&
                weatherOptions.containsAll(data.getWeatherOptions()) &&
                data.getWeatherOptions().containsAll(weatherOptions) &&
                duration.equals(data.getDuration());
    }
}
