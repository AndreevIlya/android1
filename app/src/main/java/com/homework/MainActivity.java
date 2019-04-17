package com.homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> weatherOptions = new ArrayList<>();
    private String duration = ""; //To make it not NULL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout weatherTable = findViewById(R.id.weather_table);

        String[] dataTypes = {WeatherOptions.Temperature.getName(),WeatherOptions.Precipitations.getName()};
        WeatherData.createWeekWeather(this, weatherTable, dataTypes);
        addOptionsCheckboxes();
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

    private void addOptionsCheckboxes(){
        LinearLayout weatherOptionsCheckboxes = findViewById(R.id.weatherOptionsCheckboxes);
        String[] allWeatherOptions = WeatherOptions.getAllWeatherOptions();
        for(String option : allWeatherOptions){
            CheckBox checkbox = new CheckBox(this);
            checkbox.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            checkbox.setGravity(Gravity.START);
            checkbox.setText(option);
            checkbox.setOnClickListener(new CheckboxListener());
            weatherOptionsCheckboxes.addView(checkbox);
        }
    }

    private class CheckboxListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(((CheckBox) view).isChecked()){
                weatherOptions.add(((CheckBox) view).getText().toString());
            }else{
                weatherOptions.remove(((CheckBox) view).getText().toString());
            }
        }
    }

    public void onDurationChosen(View view){
        if(((RadioButton) view).isChecked()){
            duration = view.getId() == R.id.today ? "today" : "week";
        }
    }

}
