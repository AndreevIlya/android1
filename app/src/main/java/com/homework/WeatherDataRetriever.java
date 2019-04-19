package com.homework;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class WeatherDataRetriever {
    private Map<Integer,WeatherDatum> weatherData;

    //Command pattern
    private interface WeatherDatum{
        String weatherDatum();
    }

    WeatherDataRetriever(Activity activity){
        final Activity act = activity;
        weatherData = new HashMap<>();
        weatherData.put(WeatherOptions.Temperature.getID(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20) - 10) + act.getResources().getString(R.string.degree);
            }
        });
        weatherData.put(WeatherOptions.Humidity.getID(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(70) + 30) + act.getResources().getString(R.string.percent);
            }
        });
        weatherData.put(WeatherOptions.Wind_speed.getID(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20)) + act.getResources().getString(R.string.velocity);
            }
        });
        weatherData.put(WeatherOptions.Pressure.getID(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(60) + 720) + act.getResources().getString(R.string.mmHg);
            }
        });
    }

    String getWeatherData(Integer dataType){
        return weatherData.get(dataType).weatherDatum();
    }

    Drawable getWeatherImage(Activity act){
        List<Drawable> precipitations = new ArrayList<>();
        precipitations.add(act.getResources().getDrawable(R.drawable.clear));
        precipitations.add(act.getResources().getDrawable(R.drawable.overcast));
        precipitations.add(act.getResources().getDrawable(R.drawable.cloudy));
        precipitations.add(act.getResources().getDrawable(R.drawable.snow));
        precipitations.add(act.getResources().getDrawable(R.drawable.light_rain));
        precipitations.add(act.getResources().getDrawable(R.drawable.rain));
        precipitations.add(act.getResources().getDrawable(R.drawable.heavy_rain));

        return precipitations.get(new Random().nextInt(precipitations.size()));
    }
}
