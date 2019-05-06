package com.homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        LinearLayout info;

        ViewHolder(View view) {
            super(view);
            this.date = view.findViewById(R.id.date);
            this.info = view.findViewById(R.id.weather_data);
        }
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
