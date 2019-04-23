package com.homework;

import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.Calendar;

class WeatherTableBuilder {
    private Activity activity;
    private Calendar dateToday;
    private TableLayout table;
    private Integer[] dataTypes;

    WeatherTableBuilder(Activity activity,TableLayout table,Integer[] data){
        this.activity = activity;
        this.dateToday = Calendar.getInstance();
        this.table = table;
        this.dataTypes = data;
    }

    private TableRow createWeatherRow(String date){
        TableRow weatherRow = new TableRow(activity);
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever(activity);
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(date);
        weatherRow.addView(dateTextView);
        for(Integer dataType : dataTypes){
            if (dataType.equals(WeatherOptions.Precipitations.getID())) {
                ImageView weatherDatum = new ImageView(activity,null,R.style.images);
                weatherDatum.setImageDrawable(weatherDataRetriever.getWeatherImage(activity));
                weatherRow.addView(weatherDatum);
            } else {
                TextView weatherDatum = new TextView(activity,null,R.style.weather_fields);
                Log.i("INFO",activity.getResources().getString(dataType));
                weatherDatum.setText(weatherDataRetriever.getWeatherData(dataType));
                weatherRow.addView(weatherDatum);
            }
        }
        return weatherRow;
    }

    private TableRow createTitleRow(){
        TableRow titleRow = new TableRow(activity);
        ImageView dateTextView = new ImageView(activity,null,R.style.images);
        dateTextView.setImageDrawable(activity.getResources().getDrawable(R.drawable.date));
        titleRow.addView(dateTextView);
        for(Integer dataType : dataTypes){
            ImageView weatherDatum = new ImageView(activity,null,R.style.images);
            weatherDatum.setImageDrawable(activity.getResources().getDrawable(dataType));
            titleRow.addView(weatherDatum);
        }
        return titleRow;
    }

    void createTodayWeather(){
        table.addView(createTitleRow());
        String dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
        String[] timesOfDay = {activity.getResources().getString(R.string.morning),
                activity.getResources().getString(R.string.afternoon),
                activity.getResources().getString(R.string.evening),
                activity.getResources().getString(R.string.night)};
        for(String time : timesOfDay){
            String date = dateT + " " + time;
            table.addView(createWeatherRow(date));
        }
    }

    void createWeekWeather(){
        String dateT;
        createTodayWeather();
        for(int i = 1; i < 7; i++){
            dateToday.add(Calendar.DATE, 1);
            dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
            table.addView(createWeatherRow(dateT));
        }
    }
}
