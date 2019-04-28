package com.homework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> weatherOptions = new ArrayList<>();
        weatherOptions.add(WeatherOptions.Temperature.getID());
        weatherOptions.add(WeatherOptions.Precipitations.getID());
        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            Log.i("INFO","portrait");
            TableLayout weatherTable = findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(this,weatherTable,weatherOptions);
            tableBuilder.createWeekWeather();
        }
    }
}