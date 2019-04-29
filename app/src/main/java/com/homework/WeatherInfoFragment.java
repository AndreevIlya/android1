package com.homework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Set;

public class WeatherInfoFragment extends Fragment {
    private static boolean isInit;
    static StoreData data;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View weatherContainer = inflater.inflate(R.layout.weather_info, container, false);
        data = StoreData.getSavedInstance();
        String city = data.getCity() == null ? getResources().getString(R.string.your_location) : data.getCity();
        if(isInit && data.getCity() == null && data.getDuration() != null){
            TextView viewTitle = weatherContainer.findViewById(R.id.title);
            viewTitle.setText(String.format(getResources().getString(R.string.title_in), city));

            String duration = data.getDuration();
            Set<Integer> weatherOptions = data.getWeatherOptions();
            TableLayout table = weatherContainer.findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(getActivity(), table, weatherOptions);
            if (duration.equals(getResources().getString(R.string.today))) {
                tableBuilder.createTodayWeather();
            } else if (duration.equals(getResources().getString(R.string.week))) {
                tableBuilder.createWeekWeather();
            }
        }else{
            TextView viewTitle = weatherContainer.findViewById(R.id.title);
            viewTitle.setText(String.format(getResources().getString(R.string.title_in), city));
            TableLayout table = weatherContainer.findViewById(R.id.weather_table);
            WeatherBuilder tableBuilder = new WeatherBuilder(getActivity(), table, data.getWeatherOptions());
            tableBuilder.createWeekWeather();
            isInit = true;
        }
        return weatherContainer;
    }
}
