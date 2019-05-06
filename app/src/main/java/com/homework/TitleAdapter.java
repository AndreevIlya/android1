package com.homework;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {
    private Resources resources;
    private CheckedWeatherOptions options;


    TitleAdapter(Resources resources,CheckedWeatherOptions options){
        this.resources = resources;
        this.options = options;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView) itemView;
        }
    }

    @NonNull
    @Override
    public TitleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_title_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TitleAdapter.ViewHolder viewHolder, int i) {
        if(i < options.getCheckedOptionsS().size()){
            Drawable pic = resources.getDrawable(options.getCheckedOptionsS().get(i).getPicID());
            viewHolder.iv.setImageDrawable(pic);
        }else{
            Drawable pic = resources.getDrawable(options.getCheckedOptionsD().get(i).getPicID());
            viewHolder.iv.setImageDrawable(pic);
        }
    }

    @Override
    public int getItemCount() {
        return options.getCheckedOptionsS().size() + options.getCheckedOptionsD().size();
    }
}
