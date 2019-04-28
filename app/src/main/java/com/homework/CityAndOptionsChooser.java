package com.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class CityAndOptionsChooser extends Fragment {
    private static StoreData data = StoreData.getInstance(null,new ArrayList<Integer>(),null);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentContainer = inflater.inflate(R.layout.city_and_options, container, false);
        addWeatherOptions(fragmentContainer);
        RadioButton rbToday = fragmentContainer.findViewById(R.id.today);
        rbToday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onDurationChosen(v);
            }
        });
        RadioButton rbWeek = fragmentContainer.findViewById(R.id.week);
        rbWeek.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onDurationChosen(v);
            }
        });
        Button buttonSubmit = fragmentContainer.findViewById(R.id.submitButton);
        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                data.setCity(((EditText) fragmentContainer.findViewById(R.id.cityRequested)).getText().toString());
                Log.i("INFO","size: "+data.getWeatherOptions().size());
                if(!data.getCity().isEmpty() && !data.getDuration().isEmpty() && data.getWeatherOptions().size() != 0){
                    Log.i("INFO","4");
                    showWeatherInCity();
                }
            }
        });
        return fragmentContainer;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void addWeatherOptions(View cont){
        Map<Integer,Integer> opt = initOptions();
        ViewGroup weatherOptionsCheckboxes = cont.findViewById(R.id.weatherOptionsCheckboxes);
        Integer[] allWeatherOptions = WeatherOptions.getAllWeatherOptions();
        for(Integer option : allWeatherOptions){
            CheckBox checkbox = new CheckBox(getActivity());
            checkbox.setId(option);
            checkbox.setText(this.getResources().getString(opt.get(option)));
            checkbox.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(((CheckBox) view).isChecked()){
                        data.getWeatherOptions().add(view.getId());
                    }else{
                        data.getWeatherOptions().remove(view.getId());
                    }
                }
            });
            weatherOptionsCheckboxes.addView(checkbox);
        }
    }

    public void onDurationChosen(View view){
        if(((RadioButton) view).isChecked()){
            data.setDuration(view.getId() == R.id.today ?
                    getResources().getString(R.string.today) :
                    getResources().getString(R.string.week));
        }
    }

    @SuppressLint("UseSparseArrays")
    private static Map<Integer,Integer> initOptions(){
        Map<Integer,Integer> opt = new HashMap<>();
        opt.put(R.drawable.humidity,R.string.humidity);
        opt.put(R.drawable.temperature,R.string.temperature);
        opt.put(R.drawable.weather,R.string.precipitations);
        opt.put(R.drawable.wind,R.string.wind_speed);
        opt.put(R.drawable.pressure,R.string.pressure);
        return opt;
    }

    private void showWeatherInCity(){
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE){
            WeatherInfoFragment infoFragment = (WeatherInfoFragment) getFragmentManager().findFragmentById(R.id.weather_info);;
            Log.i("INFO","5");
            if(infoFragment != null && !infoFragment.getData().areDataEqual(data)){
                Log.i("INFO","6");
                infoFragment = WeatherInfoFragment.create(data);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.weather_info, infoFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }else{
            Intent intent = new Intent(getActivity(), SecondaryActivity.class);
            intent.putExtra("DATA",data);
            startActivity(intent);
        }
    }
}
