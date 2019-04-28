package com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfoFragment extends Fragment {
    private boolean isInit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View weatherContainer = inflater.inflate(R.layout.weather_info, container, false);
        if(isInit){
            Intent intent = getActivity().getIntent();
            if(intent != null){
                StoreData data = (StoreData) getActivity().getIntent().getExtras().getSerializable("DATA");
                String city = data.getCity();
                TextView viewTitle = weatherContainer.findViewById(R.id.title_with_city);
                viewTitle.setText(String.format(getResources().getString(R.string.title_in), city));

                String duration = data.getDuration();
                List<Integer> weatherOptions = data.getWeatherOptions();
                TableLayout table = weatherContainer.findViewById(R.id.weather_table);
                WeatherBuilder tableBuilder = new WeatherBuilder(getActivity(), table, weatherOptions);
                if (duration.equals(getResources().getString(R.string.today))) {
                    tableBuilder.createTodayWeather();
                } else if (duration.equals(getResources().getString(R.string.week))) {
                    tableBuilder.createWeekWeather();
                }
            }
        }else{
            List<Integer> weatherOptions = new ArrayList<>();
            weatherOptions.add(WeatherOptions.Temperature.getID());
            weatherOptions.add(WeatherOptions.Precipitations.getID());
            TextView viewTitle = weatherContainer.findViewById(R.id.title);
            viewTitle.setText(String.format(getResources().getString(R.string.title_in), getResources().getString(R.string.your_location)));
            TableLayout table = weatherContainer.findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(getActivity(), table, weatherOptions);
            tableBuilder.createWeekWeather();
            isInit = true;
        }

        return weatherContainer;
    }

    static WeatherInfoFragment create(StoreData data){
        WeatherInfoFragment fragment = new WeatherInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable("DATA", data);
        fragment.setArguments(args);
        return fragment;
    }

    public StoreData getData() {
        return (StoreData) getArguments().getSerializable("DATA");
    }
}
