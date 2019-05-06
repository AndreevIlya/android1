package com.homework;

class WeatherOption<T> {
    private int textID;
    private int picID;
    private boolean isPicture;
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

    boolean isPicture() {
        return isPicture;
    }

    void setIsPictureTrue() {
        this.isPicture = true;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
