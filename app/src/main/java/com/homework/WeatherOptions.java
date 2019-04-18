package com.homework;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public enum WeatherOptions {
    Temperature(R.string.temperature),Precipitations(R.string.precipitations),Humidity(R.string.humidity),
    Wind_speed(R.string.wind_speed),Pressure(R.string.pressure);
    private int nameId;

    WeatherOptions(int name){
        this.nameId = name;
    }

    public int getName() {
        return nameId;
    }

    static String[] getAllWeatherOptions(Activity activity){
        List<String> allOptions = new ArrayList<>();
        for(WeatherOptions option : WeatherOptions.values()){
            allOptions.add(activity.getResources().getString(option.getName()));
        }
        return allOptions.toArray(new String[0]);
    }
}
