package com.homework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class WeatherInfoFragment extends Fragment {
    private static boolean isInit;
    static StoreData data;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View weatherContainer = inflater.inflate(R.layout.weather_info, container, false);
        Set<String> weatherOptions;
        data = StoreData.getSavedInstance();
        String city = data.getCity() == null ? getResources().getString(R.string.your_location) : data.getCity();
        if(isInit && data.getCity() != null && data.getDuration() != null){
            TextView viewTitle = weatherContainer.findViewById(R.id.title);
            viewTitle.setText(String.format(getResources().getString(R.string.title_in), city));
            weatherOptions = data.getWeatherOptions();

        }else{
            TextView viewTitle = weatherContainer.findViewById(R.id.title);
            viewTitle.setText(String.format(getResources().getString(R.string.title_in), city));
            weatherOptions = new HashSet<>();
            weatherOptions.add(getResources().getString(R.string.temperature));
            weatherOptions.add(getResources().getString(R.string.precipitations));
            data.setWeatherOptions(weatherOptions);
            data.setDuration(getResources().getString(R.string.week));
            isInit = true;
        }

        RecyclerView recyclerTitle = weatherContainer.findViewById(R.id.weather_title);
        recyclerTitle.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerTitle.setLayoutManager(layoutManager);
        CheckedWeatherOptions options = new CheckedWeatherOptions(getResources(),weatherOptions);
        TitleAdapter adapter = new TitleAdapter(getResources(),options);
        recyclerTitle.setAdapter(adapter);

        RecyclerView recyclerTable = weatherContainer.findViewById(R.id.weather_table);
        recyclerTable.setHasFixedSize(true);
        recyclerTable.setLayoutManager(layoutManager);
        WeatherAdapter weatherAdapter = new WeatherAdapter(getActivity(),getResources());
        recyclerTitle.setAdapter(weatherAdapter);

        return weatherContainer;
    }
}
