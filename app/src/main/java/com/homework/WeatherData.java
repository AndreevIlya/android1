package com.homework;

import android.app.Activity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WeatherData {
    private Map<String,WeatherDatum> weatherData;

    //Command pattern
    private interface WeatherDatum{
        String weatherDatum();
    }

    private WeatherData(){
        weatherData = new HashMap<>();
        weatherData.put(WeatherOptions.Temperature.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return Integer.toString(new Random().nextInt(20) - 10);
            }
        });
        weatherData.put(WeatherOptions.Precipitations.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                String[] residues = new String[7];
                residues[0] = ("Clear");
                residues[1] = ("Overcast");
                residues[2] = ("Cloudy");
                residues[3] = ("Snow");
                residues[4] = ("Light_rain");
                residues[5] = ("Rain");
                residues[6] = ("Heavy_rain");
                return residues[new Random().nextInt(7)];
            }
        });
        weatherData.put(WeatherOptions.Humidity.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return Integer.toString(new Random().nextInt(70) + 30) + " %";
            }
        });
        weatherData.put(WeatherOptions.Wind_speed.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return Integer.toString(new Random().nextInt(20)) + " m/s";
            }
        });
        weatherData.put(WeatherOptions.Pressure.getName(), new WeatherDatum() {
            @Override
            public String weatherDatum() {
                return Integer.toString(new Random().nextInt(60) + 720) + " mm Hg";
            }
        });
    }

    private String getWeatherData(String datatype) throws NullPointerException{
        return weatherData.get(datatype).weatherDatum();
    }

    private static TableRow createWeatherRow(Activity activity, String date, String[] dataTypes){
        TableRow weatherRow = new TableRow(activity);
        WeatherData weatherData = new WeatherData();
        List<TextView> fields = new ArrayList<>();
        TextView dateTextView = new TextView(activity);
        dateTextView.setText(date);
        fields.add(dateTextView);
        for(String dataType : dataTypes){
            TextView weatherDatum = new TextView(activity);
            weatherDatum.setText(weatherData.getWeatherData(dataType));
            fields.add(weatherDatum);
        }
        for(TextView field : fields){
            field.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            field.setGravity(Gravity.START);
            weatherRow.addView(field);
        }
        return weatherRow;
    }

    private static TableRow createTitleRow(Activity activity, String[] dataTypes){
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

    public static void createTodayWeather(Activity activity, TableLayout table, String[] dataTypes){
        table.addView(createTitleRow(activity,dataTypes));
        Calendar dateToday = Calendar.getInstance();
        String dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
        String[] timesOfDay = {"Morning","Afternoon","Evening","Night"};
        for(String time : timesOfDay){
            String date = dateT + " " + time;
            table.addView(createWeatherRow(activity,date,dataTypes));
        }
    }

    public static void createWeekWeather(Activity activity, TableLayout table, String[] dataTypes){
        table.addView(createTitleRow(activity,dataTypes));
        Calendar dateToday = Calendar.getInstance();
        String dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
        String[] timesOfDay = {"Morning","Afternoon","Evening","Night"};
        for(String time : timesOfDay){
            String date = dateT + " " + time;
            table.addView(createWeatherRow(activity,date,dataTypes));
        }
        for(int i = 1; i < 7; i++){
            dateToday.add(Calendar.DATE, 1);
            dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
            table.addView(createWeatherRow(activity,dateT,dataTypes));
        }
    }
}
