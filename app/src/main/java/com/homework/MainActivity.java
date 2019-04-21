package com.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> weatherOptions = new ArrayList<>();
    private String duration = ""; //To make it not NULL
    private Map<Integer,Integer> opt = initOptions();//Temporary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout weatherTable = findViewById(R.id.weather_table);

        Integer[] dataTypes = {WeatherOptions.Temperature.getID(),WeatherOptions.Precipitations.getID()};
        WeatherTableBuilder tableBuilder = new WeatherTableBuilder(this,weatherTable,dataTypes);
        tableBuilder.createWeekWeather();
        addWeatherOptions();
        Button button = findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String city = ((EditText) findViewById(R.id.cityRequested)).getText().toString();
                if(!city.isEmpty() && !duration.isEmpty() && weatherOptions.size() != 0){
                    StoreData data = StoreData.getInstance(city, weatherOptions,duration);
                    Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                    intent.putExtra("DATA",data);
                    startActivity(intent);
                }
            }
        });
    }

    private void addWeatherOptions(){
        LinearLayout weatherOptionsCheckboxes = findViewById(R.id.weatherOptionsCheckboxes);
        Integer[] allWeatherOptions = WeatherOptions.getAllWeatherOptions();
        for(Integer option : allWeatherOptions){
            CheckBox checkbox = new CheckBox(this);
            //,null,R.style.weather_fields
            checkbox.setId(option);
            checkbox.setText(this.getResources().getString(opt.get(option)));
            checkbox.setOnClickListener(new CheckboxListener());
            weatherOptionsCheckboxes.addView(checkbox);
        }
    }

    private class CheckboxListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(((CheckBox) view).isChecked()){
                weatherOptions.add((view).getId());
            }else{
                weatherOptions.remove((view).getId());
            }
        }
    }

    public void onDurationChosen(View view){
        if(((RadioButton) view).isChecked()){
            duration = view.getId() == R.id.today ?
                    getResources().getString(R.string.today) :
                    getResources().getString(R.string.week);
        }
    }

    private Map<Integer,Integer> initOptions(){
        opt = new HashMap<>();
        opt.put(R.drawable.humidity,R.string.humidity);
        opt.put(R.drawable.temperature,R.string.temperature);
        opt.put(R.drawable.weather,R.string.precipitations);
        opt.put(R.drawable.wind,R.string.wind_speed);
        opt.put(R.drawable.pressure,R.string.pressure);
        return opt;
    }
}
