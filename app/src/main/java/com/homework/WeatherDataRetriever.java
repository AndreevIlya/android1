package com.homework;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class WeatherDataRetriever {
    private Map<String,WeatherDatum> weatherData;

    //Command pattern
    private interface WeatherDatum{
        String weatherDatum();
    }

    WeatherDataRetriever(Activity activity){
        final Activity act = activity;
        weatherData = new HashMap<>();
        weatherData.put(act.getResources().getString(WeatherOptions.Temperature.getName()), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20) - 10) + act.getResources().getString(R.string.degree);
            }
        });
        weatherData.put(act.getResources().getString(WeatherOptions.Precipitations.getName()), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                List<String> precipitations = new ArrayList<>();
                precipitations.add(act.getResources().getString(R.string.clear));
                precipitations.add(act.getResources().getString(R.string.overcast));
                precipitations.add(act.getResources().getString(R.string.cloudy));
                precipitations.add(act.getResources().getString(R.string.snow));
                precipitations.add(act.getResources().getString(R.string.light_rain));
                precipitations.add(act.getResources().getString(R.string.rain));
                precipitations.add(act.getResources().getString(R.string.heavy_rain));
                return precipitations.get(new Random().nextInt(precipitations.size()));
            }
        });
        weatherData.put(act.getResources().getString(WeatherOptions.Humidity.getName()), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(70) + 30) + act.getResources().getString(R.string.percent);
            }
        });
        weatherData.put(act.getResources().getString(WeatherOptions.Wind_speed.getName()), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20)) + act.getResources().getString(R.string.velocity);
            }
        });
        weatherData.put(act.getResources().getString(WeatherOptions.Pressure.getName()), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(60) + 720) + act.getResources().getString(R.string.mmHg);
            }
        });
    }

    String getWeatherData(String datatype){
        return weatherData.get(datatype).weatherDatum();
    }
}
