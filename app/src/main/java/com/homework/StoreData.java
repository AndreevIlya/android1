package com.homework;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

class StoreData implements Serializable{
    private String city;
    private Set<String> weatherOptions;
    private String duration;
    private static StoreData instance;
    private static StoreData previousInstance;

    private StoreData(String city,Set<String> weatherOptions,String duration){
        this.duration = duration;
        this.city = city;
        this.weatherOptions = weatherOptions;
    }

    static StoreData getSavedInstance(){
        if(instance == null)
            instance = new StoreData(null,new HashSet<String>(),null);
        return instance;
    }

    static void setPreviousInstance(){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(instance);
            ous.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            previousInstance = (StoreData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Log.e("ERROR","Cloning error in setPreviousInstance");
            e.printStackTrace();
        }
    }

    static StoreData getPreviousInstance(){
        return previousInstance;
    }

    String getCity() {
        return city;
    }

    Set<String> getWeatherOptions() {
        return weatherOptions;
    }

    String getDuration() {
        return duration;
    }

    void setCity(String city){this.city = city;}

    void setDuration(String duration){this.duration = duration;}

    void setWeatherOptions(Set<String> weatherOptions){
        this.weatherOptions = weatherOptions;
    }

    boolean areDataPreserved(){
        return previousInstance == null ||
                instance.city.equals(previousInstance.city) &&
                instance.weatherOptions.containsAll(previousInstance.weatherOptions) &&
                previousInstance.weatherOptions.containsAll(instance.weatherOptions) &&
                instance.duration.equals(previousInstance.duration);
    }
}
