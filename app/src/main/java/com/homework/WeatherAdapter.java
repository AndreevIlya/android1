package com.homework;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private StoreData data;
    private Resources resources;
    private Activity activity;

    WeatherAdapter(Activity activity,Resources resources){
        this.data = StoreData.getSavedInstance();
        this.resources = resources;
        this.activity = activity;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        RecyclerView info;

        ViewHolder(View view) {
            super(view);
            this.date = view.findViewById(R.id.date);
            this.info = view.findViewById(R.id.weather_data);
        }
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_card, viewGroup, false);

        return new WeatherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Calendar dateToday = Calendar.getInstance();
        String dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
        switch (i){
            case 0: {
                viewHolder.date.setText(String.format("%s %s",dateT,resources.getString(R.string.morning)));
                break;
            }
            case 1: {
                viewHolder.date.setText(String.format("%s %s",dateT,resources.getString(R.string.afternoon)));
                break;
            }
            case 2: {
                viewHolder.date.setText(String.format("%s %s",dateT,resources.getString(R.string.evening)));
                break;
            }
            case 3: {
                viewHolder.date.setText(String.format("%s %s",dateT,resources.getString(R.string.night)));
                break;
            }
            default: {
                dateToday.add(Calendar.DATE, i - 3);
                dateT = dateToday.get(Calendar.DAY_OF_MONTH) +"."+ (dateToday.get(Calendar.MONTH) + 1) + "." + dateToday.get(Calendar.YEAR);
                viewHolder.date.setText(dateT);
            }
        }
        CheckedWeatherOptions options = new CheckedWeatherOptions(resources,data.getWeatherOptions());
        viewHolder.info.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        viewHolder.info.setLayoutManager(layoutManager);
        WeatherOptionItemAdapter adapter = new WeatherOptionItemAdapter(options);
        viewHolder.info.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        if(data.getDuration().equals(resources.getString(R.string.today))){
            return 4;
        }
        return 10;
    }
}
