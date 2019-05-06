package com.homework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_secondary);

        StoreData data = StoreData.getSavedInstance();
        TextView viewTitle = findViewById(R.id.title_with_city);
        viewTitle.setText(String.format(getResources().getString(R.string.title_in), data.getCity()));

        RecyclerView recyclerTitle = findViewById(R.id.weather_title);
        recyclerTitle.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerTitle.setLayoutManager(layoutManager);
        CheckedWeatherOptions options = new CheckedWeatherOptions(getResources(),data.getWeatherOptions());
        TitleAdapter adapter = new TitleAdapter(getResources(),options);
        recyclerTitle.setAdapter(adapter);

        RecyclerView recyclerTable = findViewById(R.id.weather_table);
        recyclerTable.setHasFixedSize(true);
        recyclerTable.setLayoutManager(layoutManager);
        WeatherAdapter weatherAdapter = new WeatherAdapter(this,getResources());
        recyclerTable.setAdapter(weatherAdapter);
    }
}
