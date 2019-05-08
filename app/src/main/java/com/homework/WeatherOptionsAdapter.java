package com.homework;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WeatherOptionsAdapter extends RecyclerView.Adapter<WeatherOptionsAdapter.ViewHolder>  {
    private List<String> options;
    private Set<String> checkedOptions = new HashSet<>();

    WeatherOptionsAdapter(Resources resources,List<WeatherOption<String>> optionsS,List<WeatherOption<Drawable>> optionsD){
        options = new ArrayList<>();
        for (WeatherOption<String> wo : optionsS) {
            options.add(resources.getString(wo.getTextID()));
        }
        for (WeatherOption<Drawable> wo : optionsD) {
            options.add(resources.getString(wo.getTextID()));
        }
    }

    Set<String> getCheckedOptions(){
        return checkedOptions;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;

        ViewHolder(View view) {
            super(view);
            this.checkbox = view.findViewById(R.id.option_cb);
        }
    }

    @NonNull
    @Override
    public WeatherOptionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.option_checkbox, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherOptionsAdapter.ViewHolder viewHolder, int i) {
        CheckBox cb = viewHolder.checkbox;
        final String option = options.get(i);
        cb.setText(option);
        cb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    checkedOptions.add(option);
                }else{
                    checkedOptions.add(option);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }
}
