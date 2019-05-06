package com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class CityAndOptionsChooser extends Fragment {
    static StoreData data;
    static WeatherOptionsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentContainer = inflater.inflate(R.layout.city_and_options, container, false);
        data = StoreData.getSavedInstance();
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
                data.setWeatherOptions(adapter.getCheckedOptions());
                data.setCity(((EditText) fragmentContainer.findViewById(R.id.cityRequested)).getText().toString());
                if(!data.getCity().isEmpty() && !data.getDuration().isEmpty() && data.getWeatherOptions().size() != 0){
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
        RecyclerView recyclerView = cont.findViewById(R.id.weather_options_checkboxes);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        WeatherOptions options = new WeatherOptions(getResources());
        adapter = new WeatherOptionsAdapter(getActivity().getResources(),options.getOptionsS(),options.getOptionsD());
        recyclerView.setAdapter(adapter);
        }

    public void onDurationChosen(View view){
        if(((RadioButton) view).isChecked()){
            data.setDuration(view.getId() == R.id.today ?
                    getResources().getString(R.string.today) :
                    getResources().getString(R.string.week));
        }
    }

    private void showWeatherInCity(){
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE){
            if(StoreData.getPreviousInstance() == null || !StoreData.getPreviousInstance().areDataPreserved()){
                WeatherInfoFragment infoFragment = new WeatherInfoFragment();
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
        StoreData.setPreviousInstance();
    }
}
