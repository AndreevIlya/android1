package com.homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class WeatherOptionItemAdapter extends RecyclerView.Adapter<WeatherOptionItemAdapter.ViewHolder> {
    private CheckedWeatherOptions options;

    WeatherOptionItemAdapter(CheckedWeatherOptions options){
        this.options = options;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    @NonNull
    @Override
    public WeatherOptionItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if(i < options.getCheckedOptionsS().size()){
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.weather_text_item, viewGroup, false);
        }else{
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.weather_image_item, viewGroup, false);
        }
        return new WeatherOptionItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherOptionItemAdapter.ViewHolder viewHolder, int i) {
        if(i < options.getCheckedOptionsS().size()){
            ((TextView) viewHolder.view).setText(options.getCheckedOptionsS().get(i).getValue());
        }else{
            ((ImageView) viewHolder.view).setImageDrawable(options.getCheckedOptionsD().get(i - options.getCheckedOptionsS().size()).getValue());
        }
    }

    @Override
    public int getItemCount() {
        return options.getCheckedOptionsS().size() + options.getCheckedOptionsD().size();
    }
}
