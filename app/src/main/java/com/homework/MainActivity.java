package com.homework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {
    private static boolean isNotInit = true;
    static StoreData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = StoreData.getSavedInstance();
        data.getWeatherOptions().add(WeatherOptions.Temperature.getID());
        data.getWeatherOptions().add(WeatherOptions.Precipitations.getID());
        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            TableLayout weatherTable = findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(this,weatherTable,data.getWeatherOptions());
            tableBuilder.createWeekWeather();
        }else{
            if(isNotInit) {
                Fragment fragment = new WeatherInfoFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.weather_info, fragment);
                fragmentTransaction.commit();
                isNotInit = false;
            }
        }
    }
}