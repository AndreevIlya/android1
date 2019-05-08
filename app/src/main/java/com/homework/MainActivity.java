package com.homework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TableLayout;

import java.util.HashSet;
import java.util.Set;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class MainActivity extends AppCompatActivity {
    private static boolean isNotInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StoreData data = StoreData.getSavedInstance();
        data.getWeatherOptions().add(getResources().getString(R.string.temperature));
        data.getWeatherOptions().add(getResources().getString(R.string.precipitations));
        if (getResources().getConfiguration().orientation == ORIENTATION_PORTRAIT){
            Set<String> weatherOptions = new HashSet<>();
            weatherOptions.add(getResources().getString(R.string.temperature));
            weatherOptions.add(getResources().getString(R.string.precipitations));
            data.setWeatherOptions(weatherOptions);
            data.setDuration(getResources().getString(R.string.week));

            RecyclerView recyclerTitle = findViewById(R.id.weather_title);
            recyclerTitle.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerTitle.setLayoutManager(layoutManager);
            CheckedWeatherOptions options = new CheckedWeatherOptions(getResources(),weatherOptions);
            TitleAdapter adapter = new TitleAdapter(getResources(),options);
            recyclerTitle.setAdapter(adapter);

            RecyclerView recyclerTable = findViewById(R.id.weather_table);
            recyclerTable.setHasFixedSize(true);
            recyclerTable.setLayoutManager(layoutManager);
            WeatherAdapter weatherAdapter = new WeatherAdapter(this,getResources());
            recyclerTable.setAdapter(weatherAdapter);
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