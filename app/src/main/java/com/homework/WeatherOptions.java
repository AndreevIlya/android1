package com.homework;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class WeatherOptions {
    private List<WeatherOption<String>> optionsS;
    private List<WeatherOption<Drawable>> optionsD;
    private Resources resources;

    WeatherOptions(Resources resources){
        this.resources = resources;
        try {
            optionsS = initOptionsS();
            optionsD = initOptionsD();
        } catch (DifferentSizeException e) {
            e.printStackTrace();
            Log.e("ERROR",e.getMessage());
        }
    }

    List<WeatherOption<String>> getOptionsS() {
        return optionsS;
    }

    List<WeatherOption<Drawable>> getOptionsD() {
        return optionsD;
    }

    private int[] getArray(int arrID){
        TypedArray pictures = resources.obtainTypedArray(arrID);
        int length = pictures.length();
        int[] answer = new int[length];
        for(int i = 0; i < length; i++){
            answer[i] = pictures.getResourceId(i, 0);
        }
        pictures.recycle();
        return answer;
    }

    //Tried to use generics but failed :(

    private List<WeatherOption<String>> initOptionsS() throws DifferentSizeException {
        int[] optionTexts = getArray(R.array.optionS_text);
        int[] optionPics = getArray(R.array.optionS_pics);
        List<WeatherOption<String>> options;
        if(optionPics.length == optionTexts.length){
            options = new ArrayList<>(optionTexts.length);
            for(int i = 0; i < optionTexts.length; i++){
                WeatherOption<String> wo = new WeatherOption<>(optionTexts[i],optionPics[i]);
                options.add(wo);
            }
        }else{
            throw new DifferentSizeException();
        }
        return options;
    }

    private List<WeatherOption<Drawable>> initOptionsD() throws DifferentSizeException {
        int[] optionTexts = getArray(R.array.optionD_text);
        int[] optionPics = getArray(R.array.optionD_pics);
        List<WeatherOption<Drawable>> options;
        if(optionPics.length == optionTexts.length){
            options = new ArrayList<>(optionTexts.length);
            for(int i = 0; i < optionTexts.length; i++){
                WeatherOption<Drawable> wo = new WeatherOption<>(optionTexts[i],optionPics[i]);
                options.add(wo);
            }
        }else{
            throw new DifferentSizeException();
        }
        return options;
    }




}
