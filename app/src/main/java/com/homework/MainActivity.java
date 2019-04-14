package com.homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TableLayout weatherTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherTable = findViewById(R.id.weatherTable);
        addWeatherRow("01 January 2020","Morning","-10","Snow");
        addWeatherRow("01 January 2020","Afternoon","-3","Overcast");
        addWeatherRow("01 January 2020","Evening","-11","Clear");
        addWeatherRow("01 January 2020","Night","-17","Clear");
        addWeatherRow("02 January 2020","","-8","Clear");
        addWeatherRow("03 January 2020","","-3","Cloudy");
        addWeatherRow("04 January 2020","","-1","Snow");
        addWeatherRow("05 January 2020","","-5","Cloudy");
        addWeatherRow("06 January 2020","","-7","Light snow");
        addWeatherRow("07 January 2020","","-10","Clear");

    }

    private void addWeatherRow(String date,String time,String temp, String residues){
        TableRow weatherRow = new TableRow(this);
        TextView[] fields = new TextView[4];
        for(int i =0; i < 4;i++){
            fields[i] = new TextView(this);
            fields[i].setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            fields[i].setGravity(Gravity.CENTER_HORIZONTAL);
        }
        fields[0].setText(date);
        fields[1].setText(time);
        fields[2].setText(temp);
        fields[3].setText(residues);
        weatherRow.addView(fields[0]);
        weatherRow.addView(fields[1]);
        weatherRow.addView(fields[2]);
        weatherRow.addView(fields[3]);

        this.weatherTable.addView(weatherRow);

    }
}
