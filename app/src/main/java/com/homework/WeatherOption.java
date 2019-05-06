package com.homework;

class WeatherOption<T> {
    private int textID;
    private int picID;
    private T value;

    WeatherOption(int textID,int picID){
        this.textID = textID;
        this.picID = picID;
    }

    int getTextID() {
        return textID;
    }

    int getPicID() {
        return picID;
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }
}
