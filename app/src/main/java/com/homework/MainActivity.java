package com.homework;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {
    private static boolean isNotInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Integer> weatherOptions = new ArrayList<>();
        weatherOptions.add(WeatherOptions.Temperature.getID());
        weatherOptions.add(WeatherOptions.Precipitations.getID());
        StoreData data = StoreData.getInstance(getResources().getString(R.string.your_location), weatherOptions, getResources().getString(R.string.week));
        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            TableLayout weatherTable = findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(this,weatherTable,weatherOptions);
            tableBuilder.createWeekWeather();
        }else{
            if(isNotInit) {
                Fragment fragment = WeatherInfoFragment.create(data);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.weather_info, fragment);
                fragmentTransaction.commit();
                isNotInit = false;
            }
        }
    }
}