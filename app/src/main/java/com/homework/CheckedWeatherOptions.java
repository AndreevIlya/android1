package com.homework;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class CheckedWeatherOptions {
    private List<WeatherOption<String>> checkedOptionsS = new ArrayList<>();
    private List<WeatherOption<Drawable>> checkedOptionsD = new ArrayList<>();
    private Resources resources;

    CheckedWeatherOptions(Resources resources, Set<String> requestedOptions){
        this.resources = resources;
        WeatherOptions wos = new WeatherOptions(resources);
        List<WeatherOption<String>> allwosS = wos.getOptionsS();
        for(WeatherOption<String> wo : allwosS){
            if(requestedOptions.contains(resources.getString(wo.getTextID()))){
                checkedOptionsS.add(wo);
            }
        }
        List<WeatherOption<Drawable>> allwosD = wos.getOptionsD();
        for(WeatherOption<Drawable> wo : allwosD){
            if(requestedOptions.contains(resources.getString(wo.getTextID()))){
                checkedOptionsD.add(wo);
            }
        }
        initWeatherOptionsValues();
    }

    List<WeatherOption<String>> getCheckedOptionsS() {
        return checkedOptionsS;
    }
    List<WeatherOption<Drawable>> getCheckedOptionsD() {
        return checkedOptionsD;
    }

    private interface WeatherDatumS {
        String weatherDatum();
    }

    private interface WeatherDatumD {
        Drawable weatherDatum();
    }

    private void initWeatherOptionsValues(){
        Map<Integer,WeatherDatumS> weatherS = initWeatherS();
        for(WeatherOption<String> wo : checkedOptionsS){
            wo.setValue(weatherS.get(wo.getTextID()).weatherDatum());
        }
        Map<Integer,WeatherDatumD> weatherD = initWeatherD();
        for(WeatherOption<Drawable> wo : checkedOptionsD){
            wo.setValue(weatherD.get(wo.getTextID()).weatherDatum());
        }
    }

    private Map<Integer,WeatherDatumS> initWeatherS(){
        Map<Integer,WeatherDatumS> weather = new HashMap<>();
        weather.put(R.string.temperature,new WeatherDatumS() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20) - 10) + resources.getString(R.string.degree);
            }
        });
        weather.put(R.string.humidity,new WeatherDatumS() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(70) + 30) + resources.getString(R.string.percent);
            }
        });
        weather.put(R.string.wind_speed,new WeatherDatumS() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(20)) + resources.getString(R.string.velocity);
            }
        });
        weather.put(R.string.pressure,new WeatherDatumS() {
            @Override
            public String weatherDatum() {
                return (new Random().nextInt(60) + 720) + resources.getString(R.string.mmHg);
            }
        });
        return weather;
    }

    private Map<Integer,WeatherDatumD> initWeatherD(){
        Map<Integer,WeatherDatumD> weather = new HashMap<>();
        weather.put(R.string.temperature,new WeatherDatumD() {
            @Override
            public Drawable weatherDatum() {
                TypedArray pictures = resources.obtainTypedArray(R.array.precipitation_pics);
                int length = pictures.length();
                Drawable[] prec = new Drawable[length];
                for(int i = 0; i < length; i++){
                    prec[i] = resources.getDrawable(pictures.getResourceId(i, 0));
                }
                pictures.recycle();
                return prec[new Random().nextInt(length)];
            }
        });
        return weather;
    }
}
