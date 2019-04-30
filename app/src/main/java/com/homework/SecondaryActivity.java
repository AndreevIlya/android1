package com.homework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TextView;

public class SecondaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_secondary);

        try {
            StoreData data = StoreData.getSavedInstance();
            TextView viewTitle = findViewById(R.id.title_with_city);
            viewTitle.setText(String.format(getResources().getString(R.string.title_in), data.getCity()));

            String duration = data.getDuration();
            TableLayout table = findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(this, table, data.getWeatherOptions());
            if (duration.equals(getResources().getString(R.string.today))) {
                tableBuilder.createTodayWeather();
            } else if (duration.equals(getResources().getString(R.string.week))) {
                tableBuilder.createWeekWeather();
            }
        } catch (NullPointerException e) {
            Log.e("INTENT_ERROR", "Null intent to SecondaryActivity");
        }
    }
}
