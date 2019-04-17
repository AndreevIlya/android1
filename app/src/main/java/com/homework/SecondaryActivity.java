package com.homework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_secondary);

        StoreData data = (StoreData) getIntent().getExtras().getSerializable("DATA");
        String city = data.getCity();
        TextView viewTitle = findViewById(R.id.title_with_city);
        viewTitle.setText(String.format(getResources().getString(R.string.title_with_city), city));

        String duration = data.getDuration();
        ArrayList<String> weatherOptions = data.getWeatherOptions();
        TableLayout table = findViewById(R.id.weather_table);
        if(duration.equals("today")){
            WeatherData.createTodayWeather(this,table,weatherOptions.toArray(new String[0]));
        }else if(duration.equals("week")){
            WeatherData.createWeekWeather(this,table,weatherOptions.toArray(new String[0]));
        }
    }
}
