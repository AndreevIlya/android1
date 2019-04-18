package com.homework;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class WeatherTableBuilder {
    private Activity activity;
    private Calendar dateToday;
    private TableLayout table;
    private String[] dataTypes;

    WeatherTableBuilder(Activity activity,TableLayout table,String[] data){
        this.activity = activity;
        this.dateToday = Calendar.getInstance();
        this.table = table;
        this.dataTypes = data;
    }

    private TableRow createWeatherRow(String date){
        TableRow weatherRow = new TableRow(activity);
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever();
        List<TextView> fields = new ArrayList<>();
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(date);
        fields.add(dateTextView);
        for(String dataType : dataTypes){
            TextView weatherDatum = new TextView(activity);
            weatherDatum.setText(weatherDataRetriever.getWeatherData(dataType));
            fields.add(weatherDatum);
        }
        for(TextView field : fields){
            field.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            field.setGravity(Gravity.START);
            weatherRow.addView(field);
        }
        return weatherRow;
    }

    private TableRow createTitleRow(){
        TableRow titleRow = new TableRow(activity);
        List<TextView> fields = new ArrayList<>();
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(R.string.date);
        fields.add(dateTextView);
        for(String dataType : dataTypes){
            TextView weatherDatum = new TextView(activity);
            weatherDatum.setText(dataType);
            fields.add(weatherDatum);
        }
        for(TextView field : fields){
            field.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            field.setGravity(Gravity.START);
            titleRow.addView(field);
        }
        return titleRow;
    }

    void createTodayWeather(){
        table.addView(createTitleRow());
        String dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
        String[] timesOfDay = {"Morning","Afternoon","Evening","Night"};
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
