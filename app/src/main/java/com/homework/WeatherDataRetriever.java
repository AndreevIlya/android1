package com.homework;

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

    WeatherDataRetriever(){
        weatherData = new HashMap<>();
        weatherData.put(WeatherOptions.Temperature.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20) - 10) + " \u2103";
            }
        });
        weatherData.put(WeatherOptions.Precipitations.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                List<String> precipitations = new ArrayList<>();
                precipitations.add("Clear");
                precipitations.add("Overcast");
                precipitations.add("Cloudy");
                precipitations.add("Snow");
                precipitations.add("Light rain");
                precipitations.add("Rain");
                precipitations.add("Heavy rain");
                return precipitations.get(new Random().nextInt(precipitations.size()));
            }
        });
        weatherData.put(WeatherOptions.Humidity.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(70) + 30) + " %";
            }
        });
        weatherData.put(WeatherOptions.Wind_speed.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20)) + " m/s";
            }
        });
        weatherData.put(WeatherOptions.Pressure.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(60) + 720) + " mm Hg";
            }
        });
    }

    String getWeatherData(String datatype){
        return weatherData.get(datatype).weatherDatum();
    }
}
